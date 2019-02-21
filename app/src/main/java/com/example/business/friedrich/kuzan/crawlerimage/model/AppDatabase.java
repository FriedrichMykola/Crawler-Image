package com.example.business.friedrich.kuzan.crawlerimage.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ParsedData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IParsedDataDao getIParsedDataDao();
}
