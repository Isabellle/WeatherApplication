package com.ilepez.weatherapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.ilepez.weatherapp.R;
import com.ilepez.weatherapp.adapter.FragmentStatePageSupportAdapter;
import com.ilepez.weatherapp.data.model.City;
import com.ilepez.weatherapp.utils.StringHelper;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class FragmentStatePagerSupport extends BaseActivity implements ViewPager.OnPageChangeListener{

    private static final String LOG_TAG = FragmentStatePagerSupport.class.getSimpleName();

    private FragmentStatePageSupportAdapter mAdapter;
    private ViewPager mPager;

    private ArrayList<City> cityList = new ArrayList<>();

    private String[] data = {"paris", "london", "berlin"};

    private Realm realm;

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

        realm = Realm.getDefaultInstance();

        RealmQuery<City> query = realm.where(City.class);
        final RealmResults<City> cities = query.findAll();

        Log.v(LOG_TAG, String.valueOf(cities.size()));

        mAdapter = new FragmentStatePageSupportAdapter(getSupportFragmentManager(), 3, this, data);
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(0);
        setTitle(StringHelper.capitalize(data[0]));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTitle(StringHelper.capitalize(data[position]));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}