package com.antoniocordova.kineduandroidtest.network;

import com.antoniocordova.kineduandroidtest.db.models.Activity;

import java.util.ArrayList;

public class ActivitiesResponse extends BaseResponse{

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
