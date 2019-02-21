package com.example.business.friedrich.kuzan.crawlerimage.model;

public class EBGetMaxNumber {
    private int mMaxPage;
    private int mDepth;

    public EBGetMaxNumber(int mMaxPage, int mDepth) {
        this.mMaxPage = mMaxPage;
        this.mDepth = mDepth;
    }

    public int getmDepth() {
        return mDepth;
    }

    public void setmDepth(int mDepth) {
        this.mDepth = mDepth;
    }

    public int getmMaxPage() {
        return mMaxPage;
    }

    public void setmMaxPage(int mMaxPage) {
        this.mMaxPage = mMaxPage;
    }
}
