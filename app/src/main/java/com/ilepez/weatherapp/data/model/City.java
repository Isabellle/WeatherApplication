package com.ilepez.weatherapp.data.model;

import io.realm.RealmObject;

/**
 * Created by Isabelle Lepez on 17/07/16.
 */
public class City extends RealmObject{

    private String cityName;
    private double cityLat;
    private double cityLong;

    public City() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getCityLat() {
        return cityLat;
    }

    public void setCityLat(double cityLat) {
        this.cityLat = cityLat;
    }

    public double getCityLong() {
        return cityLong;
    }

    public void setCityLong(double cityLong) {
        this.cityLong = cityLong;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", cityLat=" + cityLat +
                ", cityLong=" + cityLong +
                '}';
    }
}
