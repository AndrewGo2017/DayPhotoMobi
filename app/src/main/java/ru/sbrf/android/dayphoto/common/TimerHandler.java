package ru.sbrf.android.dayphoto.common;

import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;

import java.util.Calendar;
import ru.sbrf.android.dayphoto.model.Activity;
import ru.sbrf.android.dayphoto.model.FinishedActivity;

public class TimerHandler {
    private static volatile TimerHandler instance;

    private CountDownTimer countDownTimer;
    private AlertDialog alertDialog;
    private Calendar startTime;

    private long totalTimeInSeconds;

    private Activity activity;

    private TimerHandler() {

    }

    public static TimerHandler getCurrentInstance() {
        return instance;
    }

    public static TimerHandler getNewInstance(Activity activity) {
        TimerHandler instance = getInstance();
        instance.cancel();
        instance.setActivity(activity);
        instance.setAlertDialog(null);
        return instance;
    }

    private static TimerHandler getInstance(){
        if (instance == null) {
            synchronized (TimerHandler.class) {
                if (instance == null) {
                    instance = new TimerHandler();
                }
            }
        }
        return instance;
    }

    public void goTimer() {
        totalTimeInSeconds = 0;
        startTime = Calendar.getInstance();

        countDownTimer = new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                formatDuration(startTime, Calendar.getInstance());
                String formattedTotalTimeInSeconds = format(totalTimeInSeconds);
                if (alertDialog != null){
                    alertDialog.setMessage("Текущее время : " + formattedTotalTimeInSeconds);
                }
            }

            public void onFinish() {
                countDownTimer.start();
            }
        }.start();
    }

    private void formatDuration(Calendar start, Calendar finish) {
        totalTimeInSeconds = (finish.getTimeInMillis() - start.getTimeInMillis()) / 1000;
    }

    public static String format(long seconds) {
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%02d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }

    private void cancel() {
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
        if (this.activity != null){
            FinishedActivityHandler.save(new FinishedActivity(CurrentUser.getInstance().getUser(), this.activity, Calendar.getInstance(), this.totalTimeInSeconds));
        }
    }

    public void destroy(){
        cancel();
        instance = null;
    }

    public void setAlertDialog(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }

    public long getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }

    public String getLabel() {
        return activity.getName();
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
