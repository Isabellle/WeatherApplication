package com.ilepez.weatherapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by Isabelle Lepez on 17/07/16.
 */
public class City extends RealmObject implements Parcelable{

    private String cityName;
    private double cityLat;
    private double cityLong;
    private long timestamp;

    public City() {
    }

    protected City(Parcel in) {
        cityName = in.readString();
        cityLat = in.readDouble();
        cityLong = in.readDouble();
        timestamp = in.readLong();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", cityLat=" + cityLat +
                ", cityLong=" + cityLong +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cityName);
        parcel.writeDouble(cityLat);
        parcel.writeDouble(cityLong);
        parcel.writeLong(timestamp);
    }
}
