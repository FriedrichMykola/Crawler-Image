package com.example.business.friedrich.kuzan.crawlerimage.ui.web_search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.business.friedrich.kuzan.crawlerimage.R;
import com.example.business.friedrich.kuzan.crawlerimage.model.CrawlerWebViewClient;
import com.example.business.friedrich.kuzan.crawlerimage.model.IChangePage;
import com.example.business.friedrich.kuzan.crawlerimage.model.Parser;
import com.example.business.friedrich.kuzan.crawlerimage.ui.web_search.dialog.ParseDialogFragment;
import com.example.business.friedrich.kuzan.crawlerimage.ui.web_search.dialog.ProgressDialogFragment;

import java.util.HashMap;

public class WebSearchFragment extends MvpAppCompatFragment implements IWebSearchFragmentView {

    private WebView mWebView;
    private Button mButtonGoogle, mButtonParse, mButtonDB;

    private IChangePage mChangePage;

    public WebSearchFragment() {
    }

    @SuppressLint("ValidFragment")
    public WebSearchFragment(IChangePage mChangePage) {
        this.mChangePage = mChangePage;
    }

    @InjectPresenter
    public WebFragmentPresenter mPresenter;

    @ProvidePresenter
    WebFragmentPresenter getPresenter() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(R.string.data_add_and_parsed, getContext().getString(R.string.data_add_and_parsed));
        return new WebFragmentPresenter(new Parser(), mChangePage, map);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_parse, container, false);

        mWebView = view.findViewById(R.id.web_view_search);

        CrawlerWebViewClient client = new CrawlerWebViewClient();
        client.setmChangeUrlListener(url -> mPresenter.setmUrl(url));

        mWebView.setWebViewClient(client);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mButtonGoogle = view.findViewById(R.id.button_google);
        mButtonGoogle.setOnClickListener(v -> {
            mPresenter.setmUrl(mPresenter.URL_GOOGLE);
            mWebView.loadUrl(mPresenter.getmUrl());
        });

        mButtonParse = view.findViewById(R.id.button_parse);
        mButtonParse.setOnClickListener(v -> {
            mPresenter.addUrl(mWebView.getUrl());
            ParseDialogFragment dialogFragment = new ParseDialogFragment();
            dialogFragment.show(getFragmentManager(), null);
        });

        if (mPresenter.getmCloseDialog() != null) {
            ProgressDialogFragment dialogFragment = (ProgressDialogFragment) (getFragmentManager().findFragmentByTag(ProgressDialogFragment.TAG));
            mPresenter.setmCloseDialog(dialogFragment);
        }

        mButtonDB = view.findViewById(R.id.button_show_data);
        mButtonDB.setOnClickListener(v -> mPresenter.getmIChangePage().changePage(1, false));

        return view;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPage() {
        mWebView.loadUrl(mPresenter.getmUrl());
    }

    @Override
    public void isInternet() {

    }

    @Override
    public void openDialog() {
        ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        dialogFragment.show(getFragmentManager(), ProgressDialogFragment.TAG);
        mPresenter.setmCloseDialog(dialogFragment);
    }
}
