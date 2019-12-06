package com.antoniocordova.kineduandroidtest.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.antoniocordova.kineduandroidtest.db.models.Activity;
import com.antoniocordova.kineduandroidtest.db.models.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MyUtils {
    public static class SharedPrefereces{

        private static final String GENERAL_PREFS = "PREF_GENERAL";
        private static final String KEY_ARR_ACTIVITIES = "KEY_ARR_ACTIVITIES";
        private static final String KEY_ARR_ARTICLES = "KEY_ARR_ARTICLES";

        // Activities

        public static void saveActivitiesArray(Context context, ArrayList<Activity> arrayList){
            SharedPreferences.Editor editor = context.getSharedPreferences(GENERAL_PREFS, MODE_PRIVATE).edit();
            // Convert java object to JSON format, and returned as JSON formatted string
            String jsonString = new Gson().toJson(arrayList);
            editor.putString(KEY_ARR_ACTIVITIES, jsonString);
            editor.apply();
        }

        public static ArrayList<Activity> getActivitiesArray(Context context) {
            SharedPreferences sp = context.getSharedPreferences(GENERAL_PREFS, MODE_PRIVATE);
            String jsonString = sp.getString(KEY_ARR_ACTIVITIES, null);
            ArrayList <Activity> arrayList = new ArrayList<>();
            if(jsonString!=null){
                Type type = new TypeToken<List<Activity>>() {}.getType();
                arrayList = new Gson().fromJson(jsonString, type);
            }
            return arrayList;
        }

        // Articles

        public static void saveArticlesArray(Context context, ArrayList<Article> arrayList){
            SharedPreferences.Editor editor = context.getSharedPreferences(GENERAL_PREFS, MODE_PRIVATE).edit();
            // Convert java object to JSON format, and returned as JSON formatted string
            String jsonString = new Gson().toJson(arrayList);
            editor.putString(KEY_ARR_ARTICLES, jsonString);
            editor.apply();
        }

        public static ArrayList<Article> getArticlesArray(Context context) {
            SharedPreferences sp = context.getSharedPreferences(GENERAL_PREFS, MODE_PRIVATE);
            String jsonString = sp.getString(KEY_ARR_ARTICLES, null);
            ArrayList <Article> arrayList = new ArrayList<>();
            if(jsonString!=null){
                Type type = new TypeToken<List<Article>>() {}.getType();
                arrayList = new Gson().fromJson(jsonString, type);
            }
            return arrayList;
        }
    }
}
