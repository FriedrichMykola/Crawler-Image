package com.example.business.friedrich.kuzan.crawlerimage.application;

import android.app.Application;

import com.example.business.friedrich.kuzan.crawlerimage.dagger.DaggerIDaggerComponent;
import com.example.business.friedrich.kuzan.crawlerimage.dagger.DatabaseModule;
import com.example.business.friedrich.kuzan.crawlerimage.dagger.IDaggerComponent;

public class MyApp extends Application {

    public static IDaggerComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = DaggerIDaggerComponent
                    .builder()
                    .databaseModule(new DatabaseModule(getApplicationContext()))
                    .build();
    }
}
