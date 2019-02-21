package com.example.business.friedrich.kuzan.crawlerimage.ui.show_data;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.crawlerimage.R;
import com.example.business.friedrich.kuzan.crawlerimage.application.MyApp;
import com.example.business.friedrich.kuzan.crawlerimage.model.AppDatabase;
import com.example.business.friedrich.kuzan.crawlerimage.model.IChangePage;
import com.example.business.friedrich.kuzan.crawlerimage.model.IGetDataDB;
import com.example.business.friedrich.kuzan.crawlerimage.model.ParsedData;
import com.example.business.friedrich.kuzan.crawlerimage.model.ShowData;


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
public class ShowDataFragmentPresenter extends MvpPresenter<IShowDataFragmentView> implements IGetDataDB {

    @Inject
    @Singleton
    AppDatabase mDatabase;

    private HashMap<Integer, String> mMapMessage;

    private RecyclerViewAdapter mAdapter;

    private IChangePage mIChangePage;

    public ShowDataFragmentPresenter(IChangePage iChangePage, ShowData showData, HashMap<Integer, String> map) {
        MyApp.mComponent.Inject(this);
        showData.setmIGetDataDB(this);

        mMapMessage = map;

        mIChangePage = iChangePage;
        mAdapter = new RecyclerViewAdapter();

        getDataDB();
    }

    public IChangePage getmIChangePage() {
        return mIChangePage;
    }

    public RecyclerViewAdapter getmAdapter() {
        return mAdapter;
    }

    @Override
    public void getDataDB() {
        Observable.just(mDatabase)
                .subscribeOn(Schedulers.io())
                .map(database -> (ArrayList<ParsedData>) mDatabase.getIParsedDataDao().getAllData())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<ParsedData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<ParsedData> parsedData) {
                        mAdapter.setArrayList(parsedData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (mAdapter.getItemCount() > 0) {
                            getViewState().showMessage(mMapMessage.get(R.string.data_was_save));
                        }
                    }
                });
    }


    public void deleteAllData() {
        Observable.just(mAdapter.getmParsedData())
                .subscribeOn(Schedulers.io())
                .map(arrayList -> {
                    for (ParsedData elem : arrayList) {
                        mDatabase.getIParsedDataDao().delete(elem);
                    }
                    return arrayList;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<ParsedData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<ParsedData> arrayList) {
                        arrayList.clear();
                        mAdapter.notifyDataSetChanged();
                        getViewState().showMessage(mMapMessage.get(R.string.data_delete));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
