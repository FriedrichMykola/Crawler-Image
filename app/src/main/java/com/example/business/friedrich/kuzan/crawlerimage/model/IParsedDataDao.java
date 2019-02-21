package com.example.business.friedrich.kuzan.crawlerimage.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface IParsedDataDao {
    @Insert
    void insertAll(ArrayList<ParsedData> parsedData);

    @Delete
    void delete(ParsedData parsedData);

    @Query("SELECT * FROM parsedData")
    List<ParsedData> getAllData();
}
