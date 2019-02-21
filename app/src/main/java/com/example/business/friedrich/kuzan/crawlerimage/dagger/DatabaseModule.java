package com.example.business.friedrich.kuzan.crawlerimage.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.business.friedrich.kuzan.crawlerimage.model.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    private Context mContext;

    public DatabaseModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    AppDatabase getAppDatabase() {
        return Room.databaseBuilder(mContext, AppDatabase.class, "database").build();
    }
}
