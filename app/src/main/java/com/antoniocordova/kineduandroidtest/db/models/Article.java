package com.antoniocordova.kineduandroidtest.db.models;

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Article implements ViewModel {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("min_age")
    @Expose
    private Integer minAge;
    @SerializedName("max_age")
    @Expose
    private Integer maxAge;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("area_id")
    @Expose
    private Integer areaId;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

}