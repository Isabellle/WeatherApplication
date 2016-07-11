package com.ilepez.weatherapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ilepez.weatherapp.R;
import com.ilepez.weatherapp.adapter.DailyWeatherAdapter;
import com.ilepez.weatherapp.data.model.Daily;
import com.ilepez.weatherapp.data.model.Datum__;
import com.ilepez.weatherapp.data.model.Weather;
import com.ilepez.weatherapp.data.remote.WeatherAPI;
import com.ilepez.weatherapp.utils.Constants;
import com.ilepez.weatherapp.utils.FontCache;
import com.ilepez.weatherapp.utils.GlideBlurTransform;
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
public class WeatherFragment extends Fragment implements View.OnClickListener{

    public static final String LOG_TAG = WeatherFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private DailyWeatherAdapter mDailyWeatherAdapter;

    private ArrayList<Datum__> mArrayList = new ArrayList<>();

    private ProgressDialog mProgressDialog;

    private String city;
    private Double latitude, longitude;

    private TextView textViewCurrentDayTemp,textViewCurrentCityName;
    private String currentCityNameValue, currentDayTempValue, currentWeatherIconValue;
    private WeatherIconTextView textViewCurrentWeatherIcon;
    private ImageView imageViewCity, imageViewRefresh;

    private RelativeLayout layoutDailyDetails;

    int imageResourceId;

    public static WeatherFragment newInstance(String position, Context context) {

        WeatherFragment f = new WeatherFragment();

        Bundle args = new Bundle();
        args.putString("position", position);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(LOG_TAG, "on create veiw");

        View view = inflater.inflate(R.layout.fragment_weather, null);

        Bundle bundle = getArguments();
        city = bundle.getString("position");

        initUI(view);

        if(savedInstanceState != null){

            latitude = savedInstanceState.getDouble("latitude");
            longitude = savedInstanceState.getDouble("longitude");

            currentWeatherIconValue = savedInstanceState.getString("currentWeatherIconValue");
            currentDayTempValue = savedInstanceState.getString("currentDayTempValue");
            currentCityNameValue = savedInstanceState.getString("currentCityNameValue");

            mArrayList = savedInstanceState.getParcelableArrayList("mArrayList");

            initRecyclerView(view);

            updateUI();
        }
        else{

            getCityCoords(city);
            retrieveWeather();

            initRecyclerView(view);
        }

        setOnClickListeners();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        if(latitude != null){
            outState.putDouble("latitude",latitude);
        }

        if(longitude != null){
            outState.putDouble("latitude",longitude);
        }

        if(!currentCityNameValue.isEmpty()){
            outState.putString("currentCityNameValue",currentCityNameValue);
        }

        if(!currentDayTempValue.isEmpty()){
            outState.putString("currentDayTempValue",currentDayTempValue);
        }
        if(!currentWeatherIconValue.isEmpty()){
            outState.putString("currentWeatherIconValue",currentWeatherIconValue);
        }
        if(mArrayList != null){
            outState.putParcelableArrayList("mArrayList", mArrayList);
            Log.v(LOG_TAG, "mArrayList - "+currentCityNameValue);
        }
    }

    private void initUI(View view){

        layoutDailyDetails = (RelativeLayout)view.findViewById(R.id.layout_daily_details);

        imageViewRefresh = (ImageView) view.findViewById(R.id.imageView_refresh);
        textViewCurrentDayTemp = (TextView)view.findViewById(R.id.textview_current_day_temperature);
        textViewCurrentCityName = (TextView)view.findViewById(R.id.textview_city_name);

        textViewCurrentWeatherIcon = (WeatherIconTextView)view.findViewById(R.id.textview_current_weather_icon);
        Typeface weatherFont = FontCache.getFont(getContext(),"fonts/weathericons-regular-webfont.ttf");
        textViewCurrentWeatherIcon.setTypeface(weatherFont);

        imageViewCity = (ImageView) view.findViewById(R.id.imageViewCity);

        imageResourceId = getActivity().getResources().getIdentifier(city, "drawable", getActivity().getPackageName());
    }

    private void initRecyclerView(View view){

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_daily_weather);
        mGridLayoutManager = new GridLayoutManager(getActivity(), 5);
        mDailyWeatherAdapter = new DailyWeatherAdapter(mArrayList, getContext());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mDailyWeatherAdapter);
        mRecyclerView.setHasFixedSize(true);
    }

    private void setOnClickListeners(){

        imageViewRefresh.setOnClickListener(this);
    }

    private void getCityCoords(String city){

        switch (city) {
            case "paris":
                latitude = 50.8503;
                longitude = 4.3517;
                break;
            case "london":
                latitude = 48.8566;
                longitude = 2.3522;
                break;
            case "berlin":
                latitude = 37.7749;
                longitude = 122.4194;
                break;
            default:
                latitude = 37.7749;
                longitude = 122.4194;
        }
    }

    private void retrieveWeather() {

        WeatherAPI.Factory.getmWeatherAPI().getWeather(latitude + "," + longitude).enqueue(new Callback<Weather>() {

            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                mProgressDialog = new ProgressDialog(getActivity());
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage(getString(R.string.loadingMessage));
                mProgressDialog.show();

                int currentTemperature = StringHelper.FormatDoubleToInt(response.body().getCurrently().getTemperature());
                currentDayTempValue = currentTemperature+" °C";
                currentCityNameValue = StringHelper.capitalize(city)+" - "+response.body().getCurrently().getSummary();

                String weatherIcon = response.body().getCurrently().getIcon();
                currentWeatherIconValue = WeatherConditionCodes.fromString(weatherIcon).toString();

                updateUI();

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Daily daily =  response.body().getDaily();
                fillArrayList(daily);

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.v(LOG_TAG, "Erreur:" + getString(R.string.common_error_messsage) + t.getMessage());
            }
        });
    }

    public void updateUI(){

        textViewCurrentWeatherIcon.setText(currentWeatherIconValue);
        textViewCurrentDayTemp.setText(currentDayTempValue);
        textViewCurrentCityName.setText(currentCityNameValue);
        if (imageResourceId > 0) {
            Glide.
                    with(getContext())
                    .load(imageResourceId)
                    .centerCrop()
                    .transform(new GlideBlurTransform(getContext()))
                    .into(imageViewCity);
            imageViewCity.setColorFilter(Color.DKGRAY, PorterDuff.Mode.OVERLAY);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imageView_refresh:
                retrieveWeather();
                break;
        }
    }


    public void fillArrayList(Daily daily){
        for ( Datum__ data: daily.getData()) {
            if(mArrayList.size() < Constants.FORECAST_NUMBER_OF_DAYS){
                mArrayList.add(data);
                Log.v(LOG_TAG, data.getSummary());
            }
        }
        mDailyWeatherAdapter.notifyDataSetChanged();
    }
}