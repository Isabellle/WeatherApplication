package com.ilepez.weatherapp.activity;

import android.content.Intent;
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
import com.ilepez.weatherapp.utils.DateHelper;
import com.ilepez.weatherapp.utils.RealmHelper;

import java.io.IOException;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String LOG_TAG = SearchActivity.class.getSimpleName();
    private AutoCompleteTextView autoCompleteTextView;

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

                RealmHelper.addNewCity(description, itemLatitude, itemLongitude, DateHelper.getCurrentTimestamp());

                Intent intent = new Intent(this, FragmentStatePagerSupport.class);
                startActivity(intent);
            }
            else{
                Log.v(LOG_TAG, "Geocoder not available");
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RealmHelper.closeRealm();
    }
}
