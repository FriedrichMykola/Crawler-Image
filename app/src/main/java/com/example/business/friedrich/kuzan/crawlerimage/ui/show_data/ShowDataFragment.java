package com.example.business.friedrich.kuzan.crawlerimage.ui.show_data;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.business.friedrich.kuzan.crawlerimage.R;
import com.example.business.friedrich.kuzan.crawlerimage.model.IChangePage;
import com.example.business.friedrich.kuzan.crawlerimage.model.ShowData;

import java.util.HashMap;

public class ShowDataFragment extends MvpAppCompatFragment implements IShowDataFragmentView {

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFButtonBack, mFButtonDelete;

    private IChangePage mChangePage;
    private ShowData mShowData;

    @InjectPresenter
    public ShowDataFragmentPresenter mPresenter;

    @ProvidePresenter
    public ShowDataFragmentPresenter getShowDataFragmentPresenter() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(R.string.data_was_save, getContext().getString(R.string.data_was_save));
        map.put(R.string.data_delete, getContext().getString(R.string.data_delete));
        return new ShowDataFragmentPresenter(mChangePage, mShowData, map);
    }

    public ShowDataFragment() {
    }

    @SuppressLint("ValidFragment")
    public ShowDataFragment(IChangePage mChangePage, ShowData showData) {
        this.mChangePage = mChangePage;
        this.mShowData = showData;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_data, container, false);

        mFButtonBack = view.findViewById(R.id.button_back);
        mFButtonBack.setOnClickListener(v -> mPresenter.getmIChangePage().changePage(0, false));

        mFButtonDelete = view.findViewById(R.id.button_delete);
        mFButtonDelete.setOnClickListener(v -> {
            mPresenter.deleteAllData();
        });

        mRecyclerView = view.findViewById(R.id.recycler_view_data);
        mRecyclerView.setAdapter(mPresenter.getmAdapter());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
