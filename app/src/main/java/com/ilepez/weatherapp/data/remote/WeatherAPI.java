package com.ilepez.weatherapp.data.remote;

import com.ilepez.weatherapp.data.model.Weather;
import com.ilepez.weatherapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Isabelle Lepez on 05/07/16.
 */
public interface WeatherAPI {

    //?q=London,us&mode=json&appid="
    @GET("/forecast/"+Constants.FORECAST_IO_API_KEY+"{location}?lang="+Constants.FORECAST_IO_LANGUAGE+"&units="+Constants.FORECAST_IO_UNITS)
    Call<Weather> getWeather(@Path("location") String location);

    class Factory {

        private static WeatherAPI mWeatherAPI;

        public static WeatherAPI getmWeatherAPI() {

            if (mWeatherAPI == null) {

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(Constants.FORECAST_IO_BASE_URL)
                        .build();

                mWeatherAPI = retrofit.create(WeatherAPI.class);

                return mWeatherAPI;
            }
            else{
                return mWeatherAPI;
            }
    }
}
}
