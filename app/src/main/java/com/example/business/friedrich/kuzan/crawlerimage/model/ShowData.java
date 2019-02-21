package com.example.business.friedrich.kuzan.crawlerimage.model;

public class ShowData {
    private IGetDataDB mIGetDataDB;

    public ShowData() {
    }

    public ShowData(IGetDataDB mIGetDataDB) {
        this.mIGetDataDB = mIGetDataDB;
    }

    public IGetDataDB getmIGetDataDB() {
        return mIGetDataDB;
    }

    public void setmIGetDataDB(IGetDataDB mIGetDataDB) {
        this.mIGetDataDB = mIGetDataDB;
    }
}
