package com.antoniocordova.kineduandroidtest.network.api;

import com.antoniocordova.kineduandroidtest.network.ActivitiesResponse;
import com.antoniocordova.kineduandroidtest.network.ArticleResponse;
import com.antoniocordova.kineduandroidtest.network.ArticlesResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ApiUtils {

    private ApiUtils() { }

    private static APIService getAPIService() {
        return RetrofitClient.getClient().create(APIService.class);
    }

    public interface BasicListener {
        void onSuccess(Object response);
        void onError(String errorMessage);
    }

    /**************************************
     *
     * API methods
     *
     **************************************/

    public static void getActivities(final BasicListener callback) {
        getAPIService()
                .getActivities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ActivitiesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(ActivitiesResponse response) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    public static void getArticles(final BasicListener callback) {
        getAPIService()
                .getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticlesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(ArticlesResponse response) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    public static void getArticleDetail(long articleId, final BasicListener callback) {
        getAPIService()
                .getArticleDetail(articleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(ArticleResponse response) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() { }
                });
    }
}
