package ru.sbrf.android.dayphoto.model;

import java.util.Calendar;

public class FinishedActivity extends BaseEntity {
    private User user;

    private Activity activity;

    private Calendar today;

    private long timeInSeconds;

    public FinishedActivity(User user, Activity activity, Calendar today, long timeInSeconds) {
        this.user = user;
        this.activity = activity;
        this.today = today;
        this.timeInSeconds = timeInSeconds;
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

    public Calendar getToday() {
        return today;
    }

    public void setToday(Calendar today) {
        this.today = today;
    }

    public long getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(long timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }
}
