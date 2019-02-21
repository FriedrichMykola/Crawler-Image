package com.example.business.friedrich.kuzan.crawlerimage.ui.web_search.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.business.friedrich.kuzan.crawlerimage.R;

import java.util.HashMap;

public class ParseDialogFragment extends MvpAppCompatDialogFragment implements IParseDialogFragmentView {

    @InjectPresenter
    ParseDialogFragmentPresenter mPresenter;

    private EditText mEditMaxPage, mEditDepth;
    private Button mButton;
    private Switch mSwitchDepth;

    @ProvidePresenter
    ParseDialogFragmentPresenter getPresenter() {
        HashMap<Integer, String> listError = new HashMap<>();

        listError.put(R.string.you_do_not_input_depth, getContext().getString(R.string.you_do_not_input_depth));
        listError.put(R.string.You_do_not_input_max_page, getContext().getString(R.string.You_do_not_input_max_page));

        return new ParseDialogFragmentPresenter(listError);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_parse, container, false);

        mSwitchDepth = view.findViewById(R.id.switch_is_depth);
        mSwitchDepth.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked == true) {
                mEditDepth.setEnabled(true);
            } else {
                mEditDepth.setText("");
                mEditDepth.setEnabled(false);
            }
        });

        mEditDepth = view.findViewById(R.id.edit_max_depth);
        mEditMaxPage = view.findViewById(R.id.edit_max_number_pages);

        mButton = view.findViewById(R.id.button_ok);
        mButton.setOnClickListener(v -> {
            mPresenter.sendData(mEditMaxPage.getText().toString(), mEditDepth.getText().toString(), mSwitchDepth.isChecked());
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void closeDialog() {
        dismiss();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
