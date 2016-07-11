package com.ilepez.weatherapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ilepez.weatherapp.fragment.WeatherFragment;

/**
 * Created by Isabelle Lepez on 10/07/16.
 */
public class FragmentStatePageSupportAdapter extends FragmentStatePagerAdapter {
    private int slideCount;
    private Context context;
    private String[] data;

    public FragmentStatePageSupportAdapter(FragmentManager fm, int slideCount, Context context, String[] data) {
        super(fm);
        this.slideCount = slideCount;
        this.context = context;
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.newInstance(data[position], context);
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