package com.example.business.friedrich.kuzan.crawlerimage.ui.web_search;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.crawlerimage.R;
import com.example.business.friedrich.kuzan.crawlerimage.application.MyApp;
import com.example.business.friedrich.kuzan.crawlerimage.model.AppDatabase;
import com.example.business.friedrich.kuzan.crawlerimage.model.EBCancelParse;
import com.example.business.friedrich.kuzan.crawlerimage.model.EBGetMaxNumber;
import com.example.business.friedrich.kuzan.crawlerimage.model.IChangePage;
import com.example.business.friedrich.kuzan.crawlerimage.model.ICloseDialog;
import com.example.business.friedrich.kuzan.crawlerimage.model.ParsedData;
import com.example.business.friedrich.kuzan.crawlerimage.model.Parser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class WebFragmentPresenter extends MvpPresenter<IWebSearchFragmentView> {
    public final static String URL_GOOGLE = "https://www.google.com";

    private HashMap<Integer, String> mMapMessage;
    private ArrayList<ParsedData> mListParsedData;
    private boolean mCancelParse;

    private ArrayList<String> mUrls;

    private IChangePage mIChangePage;
    private ICloseDialog mCloseDialog;

    private String mUrl;
    private Parser mParser;


    @Inject
    @Singleton
    AppDatabase mDatabase;

    public WebFragmentPresenter(Parser parser, IChangePage changePage, HashMap<Integer, String> mapMessage) {
        EventBus.getDefault().register(this);

        mUrl = URL_GOOGLE;
        MyApp.mComponent.Inject(this);

        this.mMapMessage = mapMessage;

        this.mParser = parser;
        this.mIChangePage = changePage;

        getViewState().showPage();
    }

    public void addUrl(String url) {
        mUrls = new ArrayList<>();
        mListParsedData = new ArrayList<>();

        mUrls.add(url);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void getEBGetMaxNumber(EBGetMaxNumber maxNumber) {
        mParser.setmMaxPage(maxNumber.getmMaxPage());
        mParser.setmMaxDepth(maxNumber.getmDepth());

        getViewState().openDialog();

        beginParse(mUrls.get(0));
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void getEBCancelParse(EBCancelParse cancelParse) {
        mCancelParse = cancelParse.ismCancel();
        mCloseDialog.closeDialog();
        mCloseDialog = null;
    }

    public void beginParse(String url) {
        Observable.just(url)
                .subscribeOn(Schedulers.io())
                .map(j_url -> {
                    ParsedData parsedData = mParser.beginParse(mUrls, j_url);

                    if (parsedData != null) {
                        mListParsedData.add(parsedData);
                    }

                    return j_url;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String url) {
                        if (!mCancelParse) {
                            mUrls.remove(url);

                            if (mUrls.size() > 0) {
                                mUrl = mUrls.get(0);
                                getViewState().showPage();
                                beginParse(mUrl);
                            } else {
                                mUrls = null;
                                mParser.setmCounterWentPage(0);

                                if (mListParsedData.size() > 0) {
                                    saveData();
                                } else {
                                    mCloseDialog.closeDialog();
                                    mCloseDialog = null;
                                }
                            }
                        } else {
                            mListParsedData.clear();
                            mUrls.clear();

                            mUrl = URL_GOOGLE;
                            getViewState().showPage();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCloseDialog.closeDialog();
                        getViewState().showMessage("Parse: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public ICloseDialog getmCloseDialog() {
        return mCloseDialog;
    }

    public void setmCloseDialog(ICloseDialog mCloseDialog) {
        this.mCloseDialog = mCloseDialog;
    }

    private void saveData() {
        Observable.just(mListParsedData)
                .subscribeOn(Schedulers.io())
                .map(list -> {
                    mDatabase.getIParsedDataDao().insertAll(list);
                    return mMapMessage.get(R.string.data_add_and_parsed);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        mCloseDialog.closeDialog();
                        getViewState().showMessage(s);
                        mCloseDialog = null;
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showMessage("Save Data: " + e.getMessage());
                        mCloseDialog.closeDialog();
                        mCloseDialog = null;
                    }

                    @Override
                    public void onComplete() {
                        mIChangePage.changePage(1, true);
                    }
                });

    }

    public IChangePage getmIChangePage() {
        return mIChangePage;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmUrl() {
        return mUrl;
    }
}
