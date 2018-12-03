package ru.sbrf.android.dayphoto.common;

import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;

import java.util.Calendar;

public class TimerHandler {
    private CountDownTimer countDownTimer;
    private AlertDialog alertDialog;
    private Calendar startTime;

    private long totalTimeInSeconds = 0;

    public TimerHandler() {
        startTime = Calendar.getInstance();
    }

    public void goTimer() throws Exception {
        if (startTime == null){
            throw new Exception("no AlertDialog specified!");
        }

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                formatDuration(startTime, Calendar.getInstance());
                alertDialog.setMessage("Текущее время : " + format(totalTimeInSeconds));
            }
            public void onFinish() {
                countDownTimer.start();
            }
        }.start();
    }

    private void formatDuration(Calendar start, Calendar finish) {
        totalTimeInSeconds = ( finish.getTimeInMillis() - start.getTimeInMillis() ) / 1000;
    }

    public static String format(long seconds){
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%02d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }

    public void cancel(){
        countDownTimer.cancel();
    }

    public void setAlertDialog(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }

    public long getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }
}
