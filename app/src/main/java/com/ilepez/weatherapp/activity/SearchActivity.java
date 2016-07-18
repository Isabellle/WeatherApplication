package com.ilepez.weatherapp.activity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.ilepez.weatherapp.R;
import com.ilepez.weatherapp.adapter.GooglePlacesAutoCompleteAdapter;
import com.ilepez.weatherapp.data.model.City;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;


public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String LOG_TAG = SearchActivity.class.getSimpleName();
    private AutoCompleteTextView autoCompleteTextView;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Autcomplete Google Places
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.textview_autocomplete);
        autoCompleteTextView.setAdapter(new GooglePlacesAutoCompleteAdapter(this, R.layout.add_item_autocomplete_list_item));
        autoCompleteTextView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            final String description = (String) adapterView.getItemAtPosition(i);
            Geocoder gc = new Geocoder(this);


            if(gc.isPresent()){
                List<Address> list = null;
                try {
                    list = gc.getFromLocationName(description, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Address address = list.get(0);
                final double itemLatitude = address.getLatitude();
                final double itemLongitude = address.getLongitude();

                realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        City city = new City();
                        city.setCityLat(itemLatitude);
                        city.setCityLong(itemLongitude);
                        city.setCityName(description);
                        realm.copyToRealm(city);
                    }
                });

            }
            else{
                Log.v(LOG_TAG, "Geocoder not available");
            }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        realm.close();
    }
}
