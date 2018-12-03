package ru.sbrf.android.dayphoto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistic extends BaseEntity {

    @Expose
    @SerializedName("user")
    private User user;

    @Expose
    @SerializedName("activity")
    private Activity activity;

    //    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Expose
    @SerializedName("date")
    private String date;

    //    @DateTimeFormat(pattern = "HH:mm:ss")
    @Expose
    @SerializedName("time")
    private String time;

    public Statistic() {

    }

    public Statistic(User user, Activity activity, String date, String time) {
        this.user = user;
        this.activity = activity;
        this.date = date;
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
