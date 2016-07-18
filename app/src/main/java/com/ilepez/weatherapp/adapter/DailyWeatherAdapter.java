package com.ilepez.weatherapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ilepez.weatherapp.R;
import com.ilepez.weatherapp.data.model.weather.Datum__;
import com.ilepez.weatherapp.utils.DateHelper;
import com.ilepez.weatherapp.utils.FontCache;
import com.ilepez.weatherapp.utils.StringHelper;
import com.ilepez.weatherapp.utils.WeatherConditionCodes;

import java.util.ArrayList;

/**
 * Created by Isabelle Lepez on 07/07/16.
 */

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherViewHolder>{

    private ArrayList<Datum__> itemList = new ArrayList<>();
    private Context context;

    public DailyWeatherAdapter(ArrayList<Datum__> itemList, Context context){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public DailyWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_weather_viewholder, parent, false);
        DailyWeatherViewHolder dailyWeatherViewHolder = new DailyWeatherViewHolder(view, parent.getContext());

        return dailyWeatherViewHolder;
    }

    @Override
    public void onBindViewHolder(DailyWeatherViewHolder holder, int position) {

        String weatherIcon = itemList.get(position).getIcon();
        holder.textviewDailyWeatherIcon.setText(WeatherConditionCodes.fromString(weatherIcon).toString());

        String dayofWeek = DateHelper.getDayName(itemList.get(position).getTime(), "fr");
        holder.textViewDayOfWeek.setText(dayofWeek);

        int temperatureMax = StringHelper.FormatDoubleToInt(itemList.get(position).getApparentTemperatureMax());
        holder.textViewDailyTemperatureMax.setText(temperatureMax+" °C");

        int temperatureMin = StringHelper.FormatDoubleToInt(itemList.get(position).getApparentTemperatureMin());
        holder.textViewDailyTemperatureMin.setText(temperatureMin+" °C");
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class DailyWeatherViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDailyTemperatureMax, textViewDailyTemperatureMin,textviewDailyWeatherIcon, textViewDayOfWeek;

        public DailyWeatherViewHolder(final View itemView, Context context) {

            super(itemView);

            textViewDailyTemperatureMax = (TextView)itemView.findViewById(R.id.textView_daily_temperature_max);
            textViewDailyTemperatureMin = (TextView)itemView.findViewById(R.id.textView_daily_temperature_min);
            textviewDailyWeatherIcon = (TextView)itemView.findViewById(R.id.textview_daily_weather_icon);
            textViewDayOfWeek = (TextView)itemView.findViewById(R.id.textView_daily_day);

            Typeface weatherFont = FontCache.getFont(context,"fonts/weathericons-regular-webfont.ttf");
            textviewDailyWeatherIcon.setTypeface(weatherFont);

        }

    }
}