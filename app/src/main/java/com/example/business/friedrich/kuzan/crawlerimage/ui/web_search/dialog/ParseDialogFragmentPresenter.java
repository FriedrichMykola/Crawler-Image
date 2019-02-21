package com.example.business.friedrich.kuzan.crawlerimage.ui.web_search.dialog;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.crawlerimage.R;
import com.example.business.friedrich.kuzan.crawlerimage.model.EBGetMaxNumber;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

@InjectViewState
public class ParseDialogFragmentPresenter extends MvpPresenter<IParseDialogFragmentView> {
    private HashMap<Integer, String> mMapError;

    public ParseDialogFragmentPresenter(HashMap<Integer, String> mMapError) {
        this.mMapError = mMapError;
    }

    public void sendData(String max, String depth, boolean isDepth) {
        if ((max.length() > 0)) {
            if (isDepth) {
                if (depth.length() > 0) {
                    EventBus.getDefault().post(new EBGetMaxNumber(Integer.valueOf(max), Integer.valueOf(depth)));
                    getViewState().closeDialog();
                } else {
                    getViewState().showMessage(mMapError.get(R.string.you_do_not_input_depth));
                }
            } else  {
                EventBus.getDefault().post(new EBGetMaxNumber(Integer.valueOf(max), -1));
                getViewState().closeDialog();
            }
        } else {
            getViewState().showMessage(mMapError.get(R.string.You_do_not_input_max_page));
        }


    }
}
