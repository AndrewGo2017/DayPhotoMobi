package ru.sbrf.android.dayphoto.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ru.sbrf.android.dayphoto.model.Statistic;

public class StatisticTo  {
    @Expose
    @SerializedName("id;")
    private Long id;

    @Expose
    @SerializedName("user")
    private Long user;

    @Expose
    @SerializedName("activity")
    private Long activity;

    @Expose
    @SerializedName("date")
    private String date;

    @Expose
    @SerializedName("time")
    private String time;

    public StatisticTo() {
    }

    public StatisticTo(Long id, Long user, Long activity, String date, String time) {
        this.id = id;
        this.user = user;
        this.activity = activity;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getActivity() {
        return activity;
    }

    public void setActivity(Long activity) {
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

    public static StatisticTo getTo(Statistic statistic){
        return new StatisticTo(null, statistic.getUser().getId(), statistic.getActivity().getId(), statistic.getDate(), statistic.getTime());
    }
}
