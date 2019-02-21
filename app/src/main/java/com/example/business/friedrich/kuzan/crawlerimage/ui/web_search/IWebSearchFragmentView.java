package com.example.business.friedrich.kuzan.crawlerimage.ui.web_search;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface IWebSearchFragmentView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void showMessage(String message);

    void showPage();

    void isInternet();

    @StateStrategyType(SkipStrategy.class)
    void openDialog();
}
