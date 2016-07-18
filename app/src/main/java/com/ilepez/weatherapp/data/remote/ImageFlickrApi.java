package com.ilepez.weatherapp.data.remote;

import com.ilepez.weatherapp.data.model.picture.Picture;
import com.ilepez.weatherapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Isabelle Lepez on 13/07/16.
 */
public interface ImageFlickrApi {

    //https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=07f67894b9a3f953dec28a33473b048c&content_type=photos&lat=48.8566&lon=2.3522&per_page=1&page=1&format=json&nojsoncallback=1&api_sig=daa3f240de01e8e7ca1c8ef25866dbe7

    @GET("/?method="+Constants.FLICKR_METHOD_PHOTO_SEARCH+"&api_key="+Constants.FLICKR_API_KEY+"&content_type="+Constants.FLICKR_CONTENT_TYPE+"&lat={lat}&lon={lon}&per_page="+Constants.FLICKR_PER_PAGE+"&page="+Constants.FLICKR_NB_PAGE+"&format="+Constants.FLICKR_FORMAT+"&nojsoncallback="+Constants.FLICKR_JSON_CALLBACK+"&api_sig="+Constants.FLICKR_API_SIG)
    Call<Picture> getPicture(@Path("lat") double lat, @Path("lon") double lon);

    class Factory{

        private static ImageFlickrApi mPictureAPI;

        public static ImageFlickrApi getImageFlickApi(){

            if(mPictureAPI == null){
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(Constants.FLICKR_API_BASE_URL)
                        .build();

                mPictureAPI = retrofit.create(ImageFlickrApi.class);

                return mPictureAPI;
            }
            else{
                return mPictureAPI;
            }
        }

    }




}
