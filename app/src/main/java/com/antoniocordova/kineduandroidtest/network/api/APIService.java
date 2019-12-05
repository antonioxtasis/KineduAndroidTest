package com.antoniocordova.kineduandroidtest.network.api;

import com.antoniocordova.kineduandroidtest.network.ActivitiesResponse;
import com.antoniocordova.kineduandroidtest.network.ArticleResponse;
import com.antoniocordova.kineduandroidtest.network.ArticlesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("catalogue/activities?skill_id=5&baby_id=2064732")
    Observable<ActivitiesResponse> getActivities();

    @GET("catalogue/articles?skill_id=5&baby_id=2064732")
    Observable<ArticlesResponse> getArticles();

    @GET("articles/{articleId}")
    Observable<ArticleResponse> getArticleDetail(@Path("articleId") long articleId);
}
