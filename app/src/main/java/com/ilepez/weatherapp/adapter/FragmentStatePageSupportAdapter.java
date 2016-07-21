package com.ilepez.weatherapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ilepez.weatherapp.data.model.City;
import com.ilepez.weatherapp.fragment.WeatherFragment;

import java.util.ArrayList;

/**
 * Created by Isabelle Lepez on 10/07/16.
 */
public class FragmentStatePageSupportAdapter extends FragmentStatePagerAdapter {
    private int slideCount;
    private Context context;
    private ArrayList<City> cityArrayList = new ArrayList<>();

    public FragmentStatePageSupportAdapter(FragmentManager fm, int slideCount, Context context, ArrayList<City> cityArrayList) {
        super(fm);
        this.slideCount = slideCount;
        this.context = context;
        this.cityArrayList = cityArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.newInstance(cityArrayList.get(position), context);
    }

    @Override
    public int getCount() {
        return slideCount;
    }

    public void setData(ArrayList<City> cityArrayList) {
        this.cityArrayList = cityArrayList;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}