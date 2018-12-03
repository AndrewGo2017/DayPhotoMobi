package ru.sbrf.android.dayphoto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivityGroup extends BaseOuterEntity {
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("isActive")
    private Boolean isActive;

    public ActivityGroup(){

    }

    public ActivityGroup(long id, String name, Boolean isActive) {
        super(id);
        this.name = name;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
