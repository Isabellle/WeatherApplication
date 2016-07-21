package com.ilepez.weatherapp.utils;

import com.ilepez.weatherapp.data.model.City;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Isabelle Lepez on 18/07/16.
 */
public class RealmHelper {

    private static final String LOG_TAG = RealmHelper.class.getSimpleName();
    public static Realm realm;

    private static Realm getRealmInstance(){
        realm = Realm.getDefaultInstance();
        return realm;
    }

    public static ArrayList<City> getStoredCities(){
        RealmQuery<City> query = getRealmInstance().where(City.class);
        final RealmResults<City>results =
                realm.where(City.class)
                        .findAllSorted("timestamp", Sort.DESCENDING);

        results.size();

        ArrayList<City> cityArrayList = new ArrayList<>();

        for(int i = 0; i< results.size(); i++){
            cityArrayList.add(results.get(i));
        }

        return cityArrayList;
    }

    public static void addNewCity(final String description, final double itemLatitude, final double itemLongitude, final long timeStamp){
        getRealmInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                City city = new City();
                city.setCityLat(itemLatitude);
                city.setCityLong(itemLongitude);
                city.setCityName(description);
                city.setTimestamp(timeStamp);
                city.setTimestamp(city.getTimestamp());
                realm.copyToRealm(city);
            }
        });
    }

    public static void closeRealm(){
        realm.close();
    }

}
