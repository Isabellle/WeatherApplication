package com.ilepez.weatherapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Isabelle Lepez on 08/07/16.
 */
public class DateHelper {

    public static final String getDayName(int timeStamp, String locale)
    {
        Date date = new Date(timeStamp * 1000);
        Locale prefs = new Locale(locale, "FR");

        SimpleDateFormat df=new SimpleDateFormat("EEEE",prefs);
        return df.format(date);
    }
}
