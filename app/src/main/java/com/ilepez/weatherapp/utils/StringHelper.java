package com.ilepez.weatherapp.utils;

/**
 * Created by Isabelle Lepez on 07/07/16.
 */
public class StringHelper {

    public static String capitalize(String s) { if(s == null) return null; if(s.length() == 1){ return s.toUpperCase(); } if(s.length() > 1){ return s.substring(0,1).toUpperCase() + s.substring(1); } return ""; }

    public static String niceFormat(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }

    public static int FormatDoubleToInt(Double number){
        return (int) Math.round(number);
    }

}
