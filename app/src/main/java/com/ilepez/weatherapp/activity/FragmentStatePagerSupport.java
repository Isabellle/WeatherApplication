package com.ilepez.weatherapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilepez.weatherapp.R;
import com.ilepez.weatherapp.adapter.DailyWeatherAdapter;
import com.ilepez.weatherapp.data.model.Daily;
import com.ilepez.weatherapp.data.model.Datum__;
import com.ilepez.weatherapp.data.model.Weather;
import com.ilepez.weatherapp.data.remote.WeatherAPI;
import com.ilepez.weatherapp.utils.BlurTransform;
import com.ilepez.weatherapp.utils.Constants;
import com.ilepez.weatherapp.utils.FontCache;
import com.ilepez.weatherapp.utils.PicassoHelper;
import com.ilepez.weatherapp.utils.StringHelper;
import com.ilepez.weatherapp.utils.WeatherConditionCodes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentStatePagerSupport extends AppCompatActivity {

    MyAdapter mAdapter;
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_pager);

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        String[] data = {"paris", "london", "berlin"};

        mAdapter = new MyAdapter(getSupportFragmentManager(), 3, this, data);
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(0);
    }

    public static class MyAdapter extends FragmentStatePagerAdapter {
        private int slideCount;
        private Context context;
        private String[] data;

        public MyAdapter(FragmentManager fm, int slideCount, Context context, String[] data) {
            super(fm);
            this.slideCount = slideCount;
            this.context = context;
            this.data = data;
        }

        @Override
        public Fragment getItem(int position) {
            return myFragment.newInstance(data[position], context);
        }

        @Override
        public int getCount() {
            return slideCount;
        }

        public void setData(String[] data) {
            this.data = data;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    public static class myFragment extends Fragment implements View.OnClickListener{

        public static final String LOG_TAG = myFragment.class.getSimpleName();

        private RecyclerView mRecyclerView;
        private GridLayoutManager mGridLayoutManager;
        private DailyWeatherAdapter mDailyWeatherAdapter;

        private ArrayList<Datum__> mArrayList = new ArrayList<>();

        private ProgressDialog mProgressDialog;

        private String city;
        private Double latitude, longitude;

        private TextView textViewCurrentDayTemp,textViewCurrentDay,textViewCurrentCityName,textViewCurrentDayStatus,textViewCurrentWeatherIcon;
        private Button buttonRefresh;
        private PicassoHelper imageViewCity;
        private ImageView imageViewRefresh;

        public static myFragment newInstance(String position, Context context) {

            myFragment f = new myFragment();

            // Supply index input as an argument.
            Bundle args = new Bundle();
            args.putString("position", position);
            f.setArguments(args);

            return f;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_weather, null);

            mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_daily_weather);
            imageViewRefresh = (ImageView) view.findViewById(R.id.imageView_refresh);
            textViewCurrentDayTemp = (TextView)view.findViewById(R.id.textview_current_day_temperature);
            textViewCurrentCityName = (TextView)view.findViewById(R.id.textview_city_name);
            textViewCurrentDayStatus = (TextView)view.findViewById(R.id.textview_current_day_status);
            textViewCurrentWeatherIcon = (TextView)view.findViewById(R.id.textview_current_weather_icon);
            imageViewCity = (PicassoHelper)view.findViewById(R.id.temperature_top_area);

            mDailyWeatherAdapter = new DailyWeatherAdapter(mArrayList, getContext());
            mGridLayoutManager = new GridLayoutManager(view.getContext(), 5);
            mRecyclerView.setLayoutManager(mGridLayoutManager);
            mRecyclerView.setHasFixedSize(true);

            imageViewRefresh.setOnClickListener(this);

            Bundle bundle = getArguments();
            city = bundle.getString("position");

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

            retrieveWeather();


            return view;
        }



        private void retrieveWeather() {

            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage(getString(R.string.loadingMessage));
            mProgressDialog.show();

            WeatherAPI.Factory.getmWeatherAPI().getWeather(latitude + "," + longitude).enqueue(new Callback<Weather>() {

                @Override
                public void onResponse(Call<Weather> call, Response<Weather> response) {

                    int currentTemperature = StringHelper.FormatDoubleToInt(response.body().getCurrently().getTemperature());
                    textViewCurrentDayTemp.setText(currentTemperature+" °C");
                    textViewCurrentCityName.setText(StringHelper.capitalize(city));
                    textViewCurrentDayStatus.setText(response.body().getCurrently().getSummary());

                    Typeface weatherFont = FontCache.getFont(getContext(),"fonts/weathericons-regular-webfont.ttf");
                    textViewCurrentWeatherIcon.setTypeface(weatherFont);
                    String weatherIcon = response.body().getCurrently().getIcon();
                    textViewCurrentWeatherIcon.setText(WeatherConditionCodes.fromString(weatherIcon).toString());


                    int imageResourceId = getActivity().getResources().getIdentifier(city, "drawable", getActivity().getPackageName());
                    if (imageResourceId > 0) {
                        Picasso.
                                with(getContext())
                                .load(imageResourceId)
                                .resize(getView().getWidth(), getView().getHeight())
                                .transform(new BlurTransform(getContext()))
                                .into(imageViewCity);
                    }

                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();


                    Daily daily =  response.body().getDaily();
                    for ( Datum__ data: daily.getData()) {
                        if(mArrayList.size() < Constants.FORECAST_NUMBER_OF_DAYS){
                            mArrayList.add(data);
                        }
                    }

                    mRecyclerView.setAdapter(mDailyWeatherAdapter);

                }

                @Override
                public void onFailure(Call<Weather> call, Throwable t) {
                    Log.v(LOG_TAG, "Erreur:" + getString(R.string.common_error_messsage) + t.getMessage());
                }
            });
        }

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.imageView_refresh:
                    retrieveWeather();
                    break;
            }
        }
    }
}