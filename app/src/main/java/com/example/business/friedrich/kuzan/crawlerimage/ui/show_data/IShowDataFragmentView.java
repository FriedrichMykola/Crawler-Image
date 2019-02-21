package com.example.business.friedrich.kuzan.crawlerimage.ui.show_data;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
public interface IShowDataFragmentView extends MvpView {
    void showMessage(String message);
}
