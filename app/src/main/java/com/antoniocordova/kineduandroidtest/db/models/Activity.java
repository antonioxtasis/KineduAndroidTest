package com.antoniocordova.kineduandroidtest.db.models;

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Activity implements ViewModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("age_group")
    @Expose
    private String ageGroup;
    @SerializedName("activity_type")
    @Expose
    private String activityType;
    @SerializedName("is_holiday")
    @Expose
    private Boolean isHoliday;
    @SerializedName("domain_id")
    @Expose
    private Integer domainId;
    @SerializedName("area_id")
    @Expose
    private Integer areaId;
    @SerializedName("purpose")
    @Expose
    private String purpose;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("active_milestones")
    @Expose
    private Integer activeMilestones;
    @SerializedName("completed_milestones")
    @Expose
    private Integer completedMilestones;
    @SerializedName("dap_lifes_checked")
    @Expose
    private Boolean dapLifesChecked;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Boolean getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(Boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getActiveMilestones() {
        return activeMilestones;
    }

    public void setActiveMilestones(Integer activeMilestones) {
        this.activeMilestones = activeMilestones;
    }

    public Integer getCompletedMilestones() {
        return completedMilestones;
    }

    public void setCompletedMilestones(Integer completedMilestones) {
        this.completedMilestones = completedMilestones;
    }

    public Boolean getDapLifesChecked() {
        return dapLifesChecked;
    }

    public void setDapLifesChecked(Boolean dapLifesChecked) {
        this.dapLifesChecked = dapLifesChecked;
    }
}