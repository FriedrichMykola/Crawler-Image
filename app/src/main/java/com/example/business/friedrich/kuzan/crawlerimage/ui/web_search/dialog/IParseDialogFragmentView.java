package com.example.business.friedrich.kuzan.crawlerimage.ui.web_search.dialog;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
public interface IParseDialogFragmentView extends MvpView {
    void closeDialog();
    void showMessage(String message);
}
