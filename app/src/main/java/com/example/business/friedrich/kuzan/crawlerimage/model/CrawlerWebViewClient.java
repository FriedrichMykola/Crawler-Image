package com.example.business.friedrich.kuzan.crawlerimage.model;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CrawlerWebViewClient extends WebViewClient {

    private IChangeUrlListener mChangeUrlListener;

    public IChangeUrlListener getmChangeUrlListener() {
        return mChangeUrlListener;
    }

    public void setmChangeUrlListener(IChangeUrlListener mChangeUrlListener) {
        this.mChangeUrlListener = mChangeUrlListener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        mChangeUrlListener.changeUrlListener(request.getUrl().toString());
        return super.shouldOverrideUrlLoading(view, request);
    }
}
