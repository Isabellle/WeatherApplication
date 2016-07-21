package com.ilepez.weatherapp.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ilepez.weatherapp.R;
import com.ilepez.weatherapp.adapter.DailyWeatherAdapter;
import com.ilepez.weatherapp.data.model.City;
import com.ilepez.weatherapp.data.model.picture.Photo;
import com.ilepez.weatherapp.data.model.picture.Picture;
import com.ilepez.weatherapp.data.model.weather.Daily;
import com.ilepez.weatherapp.data.model.weather.Datum__;
import com.ilepez.weatherapp.data.model.weather.Weather;
import com.ilepez.weatherapp.data.remote.ImageFlickrApi;
import com.ilepez.weatherapp.data.remote.WeatherAPI;
import com.ilepez.weatherapp.utils.Constants;
import com.ilepez.weatherapp.utils.FontCache;
import com.ilepez.weatherapp.utils.StringHelper;
import com.ilepez.weatherapp.utils.WeatherConditionCodes;
import com.ilepez.weatherapp.utils.WeatherIconTextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Isabelle Lepez on 10/07/16.
 */
public class WeatherFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String LOG_TAG = WeatherFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private DailyWeatherAdapter mDailyWeatherAdapter;

    private ArrayList<Datum__> mArrayList = new ArrayList<>();

    private ProgressDialog mProgressDialog;

    private City city;
    private Double latitude, longitude;

    private TextView textViewCurrentDayTemp,textViewCurrentSummary;
    private String currentCityNameValue, currentDayTempValue, currentWeatherIconValue, currentSummary;
    private WeatherIconTextView textViewCurrentWeatherIcon;
    private ImageView imageViewCity;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Call<Weather> weatherCall;
    private Call<Picture> pictureCall;

    private String pictureID;
    private int imageResourceId;
    private String pictureOwner;
    private String imageURI;

    private Activity activity;

    private Photo photo;

    public static WeatherFragment newInstance(City city, Context context) {

        WeatherFragment f = new WeatherFragment();

        Bundle args = new Bundle();
        args.putParcelable("city", city);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, null);

        Bundle bundle = getArguments();
        city = bundle.getParcelable("city");
        initUI(view);

        latitude = city.getCityLat();
        longitude = city.getCityLong();
        currentCityNameValue = city.getCityName();

        if(savedInstanceState != null){

            currentWeatherIconValue = savedInstanceState.getString("currentWeatherIconValue");
            currentDayTempValue = savedInstanceState.getString("currentDayTempValue");
            currentSummary = savedInstanceState.getString("currentSummary");
            imageResourceId = savedInstanceState.getInt("imageResourceId");
            imageURI = savedInstanceState.getString("imageURI");
            Log.v(LOG_TAG, "Saved ImageURI: "+imageURI);

            mArrayList = savedInstanceState.getParcelableArrayList("mArrayList");

            initRecyclerView(view);
            displayImageFromFlickr();

            updateUI();
        }
        else{
            retrieveWeather();
            retrievePictureWeather();
            initRecyclerView(view);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelable("city", city);

        if(currentSummary != null){
            outState.putString("currentSummary",currentSummary);
        }

        if(currentDayTempValue != null){
            outState.putString("currentDayTempValue",currentDayTempValue);
        }
        if(currentWeatherIconValue != null){
            outState.putString("currentWeatherIconValue",currentWeatherIconValue);
        }
        if(mArrayList != null){
            outState.putParcelableArrayList("mArrayList", mArrayList);
        }
        if(imageURI != null){
            outState.putString("imageURI", imageURI);
            Log.v(LOG_TAG, "ImageURI: "+imageURI);
        }
    }

    private void initUI(View view){

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_swipe);
        swipeRefreshLayout.setOnRefreshListener(this);

        textViewCurrentDayTemp = (TextView)view.findViewById(R.id.textview_current_day_temperature);
        textViewCurrentSummary = (TextView)view.findViewById(R.id.textview_summary);

        textViewCurrentWeatherIcon = (WeatherIconTextView)view.findViewById(R.id.textview_current_weather_icon);
        Typeface weatherFont = FontCache.getFont(getContext(),"fonts/weathericons-regular-webfont.ttf");
        textViewCurrentWeatherIcon.setTypeface(weatherFont);

        imageViewCity = (ImageView) view.findViewById(R.id.imageViewCity);
    }

    private void initRecyclerView(View view){

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_daily_weather);
        mGridLayoutManager = new GridLayoutManager(getActivity(), 5);
        mDailyWeatherAdapter = new DailyWeatherAdapter(mArrayList, getContext());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mDailyWeatherAdapter);
        mRecyclerView.setHasFixedSize(true);
    }

    private void retrieveWeather() {

        swipeRefreshLayout.setRefreshing(true);

        latitude = city.getCityLat();
        longitude = city.getCityLong();

        weatherCall = WeatherAPI.Factory.getmWeatherAPI().getWeather(latitude + "," + longitude);
        weatherCall.enqueue(new Callback<Weather>() {

            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                activity = getActivity();

                if(activity != null && isAdded()) {

                    int currentTemperature = StringHelper.FormatDoubleToInt(response.body().getCurrently().getTemperature());
                    currentDayTempValue = currentTemperature+" °C";
                    currentSummary = response.body().getCurrently().getSummary();

                    String weatherIcon = response.body().getCurrently().getIcon();
                    currentWeatherIconValue = WeatherConditionCodes.fromString(weatherIcon).toString();

                    Daily daily =  response.body().getDaily();
                    fillArrayList(daily);

                    updateUI();

                }

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                if(activity != null && isAdded()) {
                    Log.v(LOG_TAG, "Erreur:" + getString(R.string.common_error_messsage) + t.getMessage());
                }
                weatherCall.cancel();
            }
        });

        swipeRefreshLayout.setRefreshing(false);

    }

    public void retrievePictureWeather(){

        pictureCall = ImageFlickrApi.Factory.getImageFlickApi().getPicture(latitude, longitude);
        pictureCall.enqueue(new Callback<Picture>() {
            @Override
            public void onResponse(Call<Picture> call, Response<Picture> response) {

                activity = getActivity();

                if(activity != null && isAdded()){

                    photo = response.body().getPhotos().getPhoto().get(0);
                    getImageUri(photo);
                    displayImageFromFlickr();
                }
            }

            @Override
            public void onFailure(Call<Picture> call, Throwable t) {
                if(activity != null && isAdded()){
                    Log.v(LOG_TAG, "Erreur:" + getString(R.string.common_error_messsage) + t.getMessage());
                }
                pictureCall.cancel();
            }
        });

    }

    private void getImageUri(Photo photo) {

        imageURI = "http://farm"+photo.getFarm()+".staticflickr.com/"+photo.getServer()+"/"+photo.getId()+"_"+photo.getSecret()+".jpg";

    }

    private void displayImageFromFlickr(){
        Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                Glide.with(getActivity())
                        .load(imageURI)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageViewCity);

                imageViewCity.setColorFilter(0xff888888, PorterDuff.Mode.MULTIPLY);
            }
        };

        handler.post(r);

    }

    public void updateUI(){

        int orientation = getResources().getConfiguration().orientation;

        switch(orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
                imageViewCity.setAdjustViewBounds(false);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                imageViewCity.setAdjustViewBounds(true);
                imageViewCity.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;
            default:
                imageViewCity.setAdjustViewBounds(true);
        }

        textViewCurrentWeatherIcon.setText(currentWeatherIconValue);
        textViewCurrentDayTemp.setText(currentDayTempValue);
        textViewCurrentSummary.setText(currentSummary);
    }


    public void fillArrayList(Daily daily){
        for ( Datum__ data: daily.getData()) {
            if(mArrayList.size() < Constants.FORECAST_NUMBER_OF_DAYS){
                mArrayList.add(data);
            }
        }
        mDailyWeatherAdapter.notifyDataSetChanged();
        updateUI();
    }

    @Override
    public void onDestroy() {
        if(weatherCall != null){
            weatherCall.cancel();
        }
        if(pictureCall != null){
            pictureCall.cancel();
        }
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        retrieveWeather();
    }
}
