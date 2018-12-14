package ru.sbrf.android.dayphoto.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.sbrf.android.dayphoto.model.FinishedActivity;

public class FinishedActivityHandler {

    public static void save(FinishedActivity finishedActivity) {
        SettingHandler<FinishedActivity> settingHandler = new SettingHandler<>();
        List<FinishedActivity> finishedActivities = settingHandler.retrieveSettingList("finishedActivity", FinishedActivity[].class);
        List<FinishedActivity> finishedActivitiesCommitted = settingHandler.retrieveSettingList("finishedActivitiesCommitted", FinishedActivity[].class);

        if (finishedActivities == null) {
            finishedActivities = new ArrayList<>();
        } else {
            finishedActivities = new ArrayList<>(finishedActivities);
        }

        finishedActivities.add(finishedActivity);
        settingHandler.saveSettingList("finishedActivity", finishedActivities);

        if (finishedActivitiesCommitted == null) {
            finishedActivitiesCommitted = new ArrayList<>();
        } else {
            finishedActivitiesCommitted = new ArrayList<>(finishedActivitiesCommitted);
        }
        finishedActivitiesCommitted.add(finishedActivity);
        settingHandler.saveSettingList("finishedActivitiesCommitted", finishedActivitiesCommitted);
    }

    public static String retrieve() {
        SettingHandler<FinishedActivity> settingHandler = new SettingHandler<>();
        List<FinishedActivity> finishedActivities = settingHandler.retrieveSettingList("finishedActivity", FinishedActivity[].class);
        if (finishedActivities == null) {
            return "не найдены активности за сегодня";
        }
        return countTotalTimeForToday(finishedActivities);
    }

    private static String countTotalTimeForToday(List<FinishedActivity> finishedActivities) {
        long seconds = 0;
        for (FinishedActivity finishedActivity : finishedActivities) {
            if (finishedActivity.getToday().get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR)) {
                if (finishedActivity.getUser().equals(CurrentUser.getInstance().getUser())){
                    seconds += finishedActivity.getTimeInSeconds();
                }
            }
        }
        return TimerHandler.format(seconds);
    }
}
