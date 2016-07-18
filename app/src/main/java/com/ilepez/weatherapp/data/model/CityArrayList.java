package com.ilepez.weatherapp.data.model;

import java.util.ArrayList;

/**
 * Created by Isabelle Lepez on 17/07/16.
 */
public class CityArrayList {

    public ArrayList<City> cityList;

    private CityArrayList() {
        cityList = new ArrayList<City>();
    }

    public ArrayList<City> getCityList() {
        return cityList;
    }

    private static CityArrayList instance;

    public static CityArrayList getInstance() {
        if (instance == null) instance = new CityArrayList();
        return instance;
    }

}
