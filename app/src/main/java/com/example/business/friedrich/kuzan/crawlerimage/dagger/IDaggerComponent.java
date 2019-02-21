package com.example.business.friedrich.kuzan.crawlerimage.dagger;

import com.example.business.friedrich.kuzan.crawlerimage.ui.main.MainActivityPresenter;
import com.example.business.friedrich.kuzan.crawlerimage.ui.show_data.ShowDataFragmentPresenter;
import com.example.business.friedrich.kuzan.crawlerimage.ui.web_search.WebFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DatabaseModule.class})
public interface IDaggerComponent {
    void Inject(WebFragmentPresenter presenter);

    void Inject(ShowDataFragmentPresenter presenter);
}
