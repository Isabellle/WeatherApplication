package com.ilepez.weatherapp.utils;

/**
 * Created by Isabelle Lepez on 04/07/16.
 */
public class Constants {

    public static final int FORECAST_NUMBER_OF_DAYS  = 5;

    public static final String FORECAST_IO_API_KEY = "abf7afb3c49204d542b14e8ba282a188/";
    public static final String FORECAST_IO_BASE_URL = "https://api.forecast.io";
    public static final String FORECAST_IO_UNITS = "si";
    public static final String FORECAST_IO_LANGUAGE = "fr";

    /*Flickr API credentials*/
    public static final String FLICKR_API_KEY = "21a70f880903999375f2417f0a849f1d";
    public static final String FLICKR_SECRET_KEY = "c2f1e7e5755aca05";
    public static final String FLICKR_API_BASE_URL = "https://api.flickr.com/services/rest";
    public static final String FLICKR_USER_ID = "144929717@N06";
    public static final String FLICKR_METHOD_PHOTO_SEARCH = "flickr.photos.search";
    public static final String FLICKR_CONTENT_TYPE = "photos";
    public static final String FLICKR_FORMAT = "json";
    public static final String FLICKR_API_SIG = "daa3f240de01e8e7ca1c8ef25866dbe7";
    public static final int FLICKR_JSON_CALLBACK = 1;
    public static final int FLICKR_PER_PAGE = 1;
    public static final int FLICKR_NB_PAGE = 1;

    //https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=07f67894b9a3f953dec28a33473b048c&content_type=photos
    // &lat=48.8566&lon=2.3522&per_page=1&page=1&format=json&nojsoncallback=1&api_sig=daa3f240de01e8e7ca1c8ef25866dbe7


    public static final String GOOGLE_API_KEY = "AIzaSyDWLHDziTZ7ClpId208sKT5xo1M5yDPucY";
    public final static String GOOGLE_PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    public final static String GOOGLE_PLACES_TYPE_AUTOCOMPLETE = "/autocomplete";
    public final static String GOOGLE_PLACES_LANGUAGE = "fr";
    public final static String GOOGLE_PLACES_OUT_JSON = "/json";
    //public final static String GOOGLE_PLACES_COUNTRY = "be";

    /*Shared preferences*/
    public static final String EXTRA_APP_PREFRENCES_LABEL = "weatherapp";
    public static final String EXTRA_CITY_SET_NAME = "EXTRA_CITY_SET";

}
