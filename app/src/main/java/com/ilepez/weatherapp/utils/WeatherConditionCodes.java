package com.ilepez.weatherapp.utils;

/**
 * Created by Isabelle Lepez on 06/07/16.
 */
public enum WeatherConditionCodes {

    CLEAR_NIGHT("clear-night", 0xf02e),
    CLEAR_DAY("clear-day",0xf00d),
    PARTLY_CLOUDY_NIGHT("partly-cloudy-night", 0xf086),
    PARTLY_CLOUDY_DAY("partly-cloudy-day", 0xf013),
    DAY_SUNNY("day-sunny", 0xf00d),
    NIGHT_CLEAR("night-clear", 0xf02e),
    RAIN("rain", 0xf019),
    SNOW("snow", 0xf01b),
    SLEET("sleet", 0xf0b5),
    STRONG_WIND("strong_wind", 0x050),
    FOG("fog", 0xf014),
    CLOUDY("cloudy", 0xf013),
    DAY_CLOUDY("day-cloudy", 0xf002),
    NIGHT_CLOUDY("night-cloudy", 0xf031),
    HAIL("hail", 0xf015),
    THUNDERSTORM("thunderstorm", 0xf01e),
    TORNADO("tornado", 0xf056),
    WIND("wind", 0xf021);

    String value;
    int character;

    WeatherConditionCodes(String value, int character) {

        this.value = value;
        this.character = character;
    }

    public static WeatherConditionCodes fromString(String weatherIconValue) {

        for (WeatherConditionCodes w : WeatherConditionCodes.values()) {
            if (w.value.equals(weatherIconValue)) {
                return w;
            }
        }

        return null;
    }

    public String toString() {
        return Character.toString((char) character);
    }

}
