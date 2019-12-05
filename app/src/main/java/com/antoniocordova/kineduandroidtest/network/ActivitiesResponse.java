package com.antoniocordova.kineduandroidtest.network;

import com.antoniocordova.kineduandroidtest.db.models.Activity;

import java.util.ArrayList;

public class ActivitiesResponse {

    public class Meta {
        int page;
        int per_page;
        int total_pages;
        int total;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public class Data {
        int id;
        String name;
        String type;
        ArrayList<Activity> activities = new ArrayList<>();

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

        public ArrayList<Activity> getActivities() {
            return activities;
        }

        public void setActivities(ArrayList<Activity> activities) {
            this.activities = activities;
        }
    }

    Data data;
    Meta meta;


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
