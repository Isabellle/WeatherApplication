package com.ilepez.weatherapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.ilepez.weatherapp.R;
import com.ilepez.weatherapp.adapter.FragmentStatePageSupportAdapter;
import com.ilepez.weatherapp.data.model.City;
import com.ilepez.weatherapp.utils.RealmHelper;
import com.ilepez.weatherapp.utils.StringHelper;

import java.util.ArrayList;

public class FragmentStatePagerSupport extends BaseActivity implements ViewPager.OnPageChangeListener{

    private static final String LOG_TAG = FragmentStatePagerSupport.class.getSimpleName();

    private FragmentStatePageSupportAdapter mAdapter;
    private ViewPager mPager;

    private ArrayList<City> cityArrayList = new ArrayList<>();
    //private String[] data = {"paris", "london", "berlin"};

    @Override
    public FragmentManager getSupportFragmentManager() {
        return super.getSupportFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.fragment_pager, frameLayout);

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(this);

        cityArrayList = RealmHelper.getStoredCities();

        mAdapter = new FragmentStatePageSupportAdapter(getSupportFragmentManager(), cityArrayList.size(), this, cityArrayList);
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RealmHelper.getStoredCities();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTitle(StringHelper.capitalize(cityArrayList.get(position).getCityName()));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}