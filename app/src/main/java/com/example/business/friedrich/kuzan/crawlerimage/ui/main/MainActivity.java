package com.example.business.friedrich.kuzan.crawlerimage.ui.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.business.friedrich.kuzan.crawlerimage.R;
import com.example.business.friedrich.kuzan.crawlerimage.model.ShowData;
import com.example.business.friedrich.kuzan.crawlerimage.ui.show_data.ShowDataFragment;
import com.example.business.friedrich.kuzan.crawlerimage.ui.web_search.WebSearchFragment;

public class MainActivity extends MvpAppCompatActivity implements IMainActivityView {

    private ViewPager mViewPager;

    @InjectPresenter
    MainActivityPresenter mPresenter;

    @ProvidePresenter
    MainActivityPresenter getPresenter() {
        return new MainActivityPresenter(new ShowData());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager_main);

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WebSearchFragment(mPresenter));
        adapter.addFragment(new ShowDataFragment(mPresenter, mPresenter.getmShowData()));
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void openPage(int position, boolean isNew) {
        if (isNew) {
            mPresenter.getmShowData().getmIGetDataDB().getDataDB();
        }
        mViewPager.setCurrentItem(position);
    }


}
