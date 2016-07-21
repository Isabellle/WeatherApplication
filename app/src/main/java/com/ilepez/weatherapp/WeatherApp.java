package com.ilepez.weatherapp;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Isabelle Lepez on 18/07/16.
 */
public class WeatherApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initializeStetho(this);

        //------ init the db ----------------
        RealmConfiguration config = new RealmConfiguration
                .Builder(this)
                .deleteRealmIfMigrationNeeded()
                .name("WeathAppDB.realm").build();
        Realm.setDefaultConfiguration(config);
    }

    private void initializeStetho(final Context context) {

        Stetho.initialize(Stetho.newInitializerBuilder(context)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(context)
                        .withDescendingOrder()
                        .withLimit(1000)
                        .databaseNamePattern(Pattern.compile(".+\\.realm"))
                        .build())
                .build());
    }
}
