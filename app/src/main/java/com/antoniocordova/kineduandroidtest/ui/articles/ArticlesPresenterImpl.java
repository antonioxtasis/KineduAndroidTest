package com.antoniocordova.kineduandroidtest.ui.articles;

import com.antoniocordova.kineduandroidtest.network.ArticlesResponse;
import com.antoniocordova.kineduandroidtest.network.api.ApiUtils;

public class ArticlesPresenterImpl implements ArticlesPresenter{
    /**
     * Communicate: Impl --> "Presenter" --> UI
     */

    public interface IViewEvents {
        void onGetArticles(ArticlesResponse response);
        void onError(Throwable e);
    }

    /**
     * Implementer
     */

    private ArticlesPresenterImpl.IViewEvents view;

    ArticlesPresenterImpl(ArticlesPresenterImpl.IViewEvents iv) {
        this.view = iv;
    }

    /**
     * Methods
     */

    @Override
    public void getArticles() {
        ApiUtils.getArticles(new ApiUtils.BasicListener() {
            @Override
            public void onSuccess(Object resp) {
                ArticlesResponse response = (ArticlesResponse) resp;
                view.onGetArticles(response);
            }

            @Override
            public void onError(Throwable e) {
                view.onError(e);
            }
        });
    }
}
