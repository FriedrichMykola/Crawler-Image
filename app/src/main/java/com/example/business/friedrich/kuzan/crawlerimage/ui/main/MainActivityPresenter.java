package com.example.business.friedrich.kuzan.crawlerimage.ui.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.crawlerimage.model.IChangePage;
import com.example.business.friedrich.kuzan.crawlerimage.model.ShowData;
import com.example.business.friedrich.kuzan.crawlerimage.ui.show_data.ShowDataFragment;
import com.example.business.friedrich.kuzan.crawlerimage.ui.web_search.WebSearchFragment;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<IMainActivityView> implements IChangePage {
    private ShowData mShowData;

    public MainActivityPresenter(ShowData mShowData) {
        this.mShowData = mShowData;
    }

    public ShowData getmShowData() {
        return mShowData;
    }

    public void setmShowData(ShowData mShowData) {
        this.mShowData = mShowData;
    }

    @Override
    public void changePage(int position, boolean isNew) {
        getViewState().openPage(position, isNew);
    }
}
