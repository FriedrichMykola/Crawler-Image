package com.example.business.friedrich.kuzan.crawlerimage.ui.web_search.dialog;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.business.friedrich.kuzan.crawlerimage.R;
import com.example.business.friedrich.kuzan.crawlerimage.model.EBCancelParse;
import com.example.business.friedrich.kuzan.crawlerimage.model.ICloseDialog;

import org.greenrobot.eventbus.EventBus;

import javax.annotation.Nullable;

public class ProgressDialogFragment extends MvpAppCompatDialogFragment implements ICloseDialog {
    public final static String TAG = "Progress";

    private Button mButtonCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_progress, container, false);
        mButtonCancel = view.findViewById(R.id.button_cancel);
        mButtonCancel.setOnClickListener(v -> EventBus.getDefault().post(new EBCancelParse(true)));
        getDialog().setCanceledOnTouchOutside(false);
        return view;
    }

    @Override
    public void onStart() {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onStart();
    }

    @Override
    public void closeDialog() {
        dismiss();
    }

}
