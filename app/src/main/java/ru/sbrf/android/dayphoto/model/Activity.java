package ru.sbrf.android.dayphoto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity extends BaseOuterEntity {
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("activityGroup")
    private ActivityGroup activityGroup;

    @Expose
    @SerializedName("isActive")
    private boolean isActive;

    public Activity(){

    }

    public Activity(long id, String name, ActivityGroup activityGroup, boolean isActive) {
        super(id);
        this.name = name;
        this.activityGroup = activityGroup;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActivityGroup getActivityGroup() {
        return activityGroup;
    }

    public void setActivityGroup(ActivityGroup activityGroup) {
        this.activityGroup = activityGroup;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
