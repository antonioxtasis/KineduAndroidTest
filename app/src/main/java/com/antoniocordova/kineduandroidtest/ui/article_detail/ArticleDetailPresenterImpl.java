package com.antoniocordova.kineduandroidtest.ui.article_detail;

import com.antoniocordova.kineduandroidtest.network.ArticleDetailResponse;
import com.antoniocordova.kineduandroidtest.network.api.ApiUtils;

public class ArticleDetailPresenterImpl implements ArticleDetailPresenter {
    /**
     * Communicate: Impl --> "Presenter" --> UI
     */

    public interface IViewEvents {
        void onGetArticleDetail(ArticleDetailResponse response);
        void onError(String errorMessage);
    }

    /**
     * Implementer
     */

    private ArticleDetailPresenterImpl.IViewEvents view;

    ArticleDetailPresenterImpl(ArticleDetailPresenterImpl.IViewEvents iv) {
        this.view = iv;
    }

    /**
     * Methods
     */

    @Override
    public void getArticleDetail(long articleId) {
        ApiUtils.getArticleDetail(articleId, new ApiUtils.BasicListener() {
            @Override
            public void onSuccess(Object resp) {
                ArticleDetailResponse response = (ArticleDetailResponse) resp;
                view.onGetArticleDetail(response);
            }

            @Override
            public void onError(String errorMessage) {
                view.onError(errorMessage);
            }
        });
    }
}
