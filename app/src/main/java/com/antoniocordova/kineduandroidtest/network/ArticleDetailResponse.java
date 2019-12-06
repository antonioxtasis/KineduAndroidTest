package com.antoniocordova.kineduandroidtest.network;

import com.antoniocordova.kineduandroidtest.db.models.Activity;
import com.antoniocordova.kineduandroidtest.db.models.Article;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArticleDetailResponse {
    public class Data {

        public class RelatedItems{
            ArrayList<Activity> activities = new ArrayList<>();
            ArrayList<Article> articles = new ArrayList<>();

            public ArrayList<Activity> getActivities() {
                return activities;
            }

            public void setActivities(ArrayList<Activity> activities) {
                this.activities = activities;
            }

            public ArrayList<Article> getArticles() {
                return articles;
            }

            public void setArticles(ArrayList<Article> articles) {
                this.articles = articles;
            }
        }

        @SerializedName("type")
        @Expose
        RelatedItems relatedItems;
        Article article;

        public RelatedItems getRelatedItems() {
            return relatedItems;
        }

        public void setRelatedItems(RelatedItems relatedItems) {
            this.relatedItems = relatedItems;
        }

        public Article getArticle() {
            return article;
        }

        public void setArticle(Article article) {
            this.article = article;
        }
    }

    Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
