package com.example.business.friedrich.kuzan.crawlerimage.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ParsedData {
    @PrimaryKey(autoGenerate = true)
    public int mId;

    public int mNumberTags;
    public double mTime;
    public String mURL;

    public ParsedData() {
    }

    public ParsedData(int mNumberTags, double mTime, String mURL) {
        this.mNumberTags = mNumberTags;
        this.mTime = mTime;
        this.mURL = mURL;
    }

    public ParsedData(int mId, int mNumberTags, double mTime, String mURL) {
        this.mId = mId;
        this.mNumberTags = mNumberTags;
        this.mTime = mTime;
        this.mURL = mURL;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmNumberTags() {
        return mNumberTags;
    }

    public void setmNumberTags(int mNumberTags) {
        this.mNumberTags = mNumberTags;
    }

    public double getmTime() {
        return mTime;
    }

    public void setmTime(double mTime) {
        this.mTime = mTime;
    }

    public String getmURL() {
        return mURL;
    }

    public void setmURL(String mURL) {
        this.mURL = mURL;
    }
}
