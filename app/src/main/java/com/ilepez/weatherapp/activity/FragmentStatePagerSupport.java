package com.ilepez.weatherapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ilepez.weatherapp.R;
import com.ilepez.weatherapp.adapter.FragmentStatePageSupportAdapter;

public class FragmentStatePagerSupport extends AppCompatActivity {

    private static final String LOG_TAG = FragmentStatePagerSupport.class.getSimpleName();

    private FragmentStatePageSupportAdapter mAdapter;
    private ViewPager mPager;

    @Override
    public FragmentManager getSupportFragmentManager() {
        return super.getSupportFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_pager);

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        String[] data = {"paris", "london", "berlin"};

        mAdapter = new FragmentStatePageSupportAdapter(getSupportFragmentManager(), 3, this, data);
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(0);
    }
}