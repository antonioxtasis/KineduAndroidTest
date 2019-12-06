package com.antoniocordova.kineduandroidtest.network;

import com.antoniocordova.kineduandroidtest.db.models.Article;

import java.util.ArrayList;

public class ArticlesResponse {
    public class Data {
        int id;
        String name;
        String type;
        ArrayList<Article> articles = new ArrayList<>();

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public ArrayList<Article> getArticles() {
            return articles;
        }

        public void setArticles(ArrayList<Article> articles) {
            this.articles = articles;
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
