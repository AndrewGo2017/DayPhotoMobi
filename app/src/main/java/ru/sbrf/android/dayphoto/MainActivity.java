package ru.sbrf.android.dayphoto;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.sbrf.android.dayphoto.common.CurrentUser;
import ru.sbrf.android.dayphoto.common.LoadEffectHandler;
import ru.sbrf.android.dayphoto.common.Preferences;
import ru.sbrf.android.dayphoto.common.SettingHandler;
import ru.sbrf.android.dayphoto.common.TimerHandler;
import ru.sbrf.android.dayphoto.controller.ActivityGetController;
import ru.sbrf.android.dayphoto.controller.ActivityGroupGetController;
import ru.sbrf.android.dayphoto.controller.StatisticController;
import ru.sbrf.android.dayphoto.controller.UserGetController;
import ru.sbrf.android.dayphoto.dialog.ActivityInProgressDialog;
import ru.sbrf.android.dayphoto.dialog.LoadEffectDialog;
import ru.sbrf.android.dayphoto.dialog.TotalActivityTimeDialog;
import ru.sbrf.android.dayphoto.dialog.UsersDialog;
import ru.sbrf.android.dayphoto.model.ActivityGroup;
import ru.sbrf.android.dayphoto.model.FinishedActivity;
import ru.sbrf.android.dayphoto.model.Statistic;
import ru.sbrf.android.dayphoto.model.User;
import ru.sbrf.android.dayphoto.util.CommonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LoadEffectHandler loadEffectHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("");

        Preferences.getInstance().setPreferences(getPreferences(MODE_PRIVATE));

        loadEffectHandler = new LoadEffectHandler(getLayoutInflater(), (ViewGroup) findViewById(R.id.layout_root));
        loadEffectHandler.setWindowToLock(getWindow());
        init();
    }

    public void init() {
        final ViewGroup viewGroupMain = (ViewGroup) findViewById(R.id.activity_group_linear);
        final MainActivity context = this;

        loadEffectHandler.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                loadSavedData();

                final List<ActivityGroup> activityGroups = ActivityGroupGetController.getInstance().getAllFromCache();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (final ActivityGroup activityGroup : activityGroups) {
                            Button activityGroupButton = new Button(context);
                            activityGroupButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(MainActivity.this, ActivityDetail.class);
                                    intent.putExtra("activityGroup", activityGroup);
                                    startActivity(intent);
                                }
                            });
                            activityGroupButton.setText(activityGroup.getName());

                            viewGroupMain.addView(activityGroupButton);
                        }

                        User currentUser = CurrentUser.getInstance().getUser();
                        if (currentUser == null) {
                            new UsersDialog(context, R.layout.dialog, false).createDialog().show();
                        } else {
                            getSupportActionBar().setTitle(currentUser.getName());
                        }

                        loadEffectHandler.hide();
                    }
                });
            }
        }).start();
    }

    public void loadSavedData() {
        ActivityGroupGetController activityGroupController = ActivityGroupGetController.getInstance();
        activityGroupController.cacheFromSharedPreferences(Preferences.getInstance().getPreferences(), "activityGroups");

        ActivityGetController activityController = ActivityGetController.getInstance();
        activityController.cacheFromSharedPreferences(Preferences.getInstance().getPreferences(), "activities");

        UserGetController userController = UserGetController.getInstance();
        userController.cacheFromSharedPreferences(Preferences.getInstance().getPreferences(), "users");

        final User currentUser = new SettingHandler<User>().retrieveSetting("currentUser", User.class);
        CurrentUser.getInstance().setUser(currentUser);
        if (currentUser != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getSupportActionBar().setTitle(currentUser.getName());
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_show_total_day_time:
                AlertDialog totalActivityTimeDialog = new TotalActivityTimeDialog(this, R.layout.dialog).createDialog();
                totalActivityTimeDialog.show();
                return true;
            case R.id.menu_item_submit_refresh:
                final AlertDialog refreshLoadEffectDialog = new LoadEffectDialog(this, R.layout.dialog, "Обновление...").createDialog();
                refreshLoadEffectDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ActivityGroupGetController.getInstance().refresh(Preferences.getInstance().getPreferences(), "activityGroups");
                        ActivityGetController.getInstance().refresh(Preferences.getInstance().getPreferences(), "activities");
                        UserGetController.getInstance().refresh(Preferences.getInstance().getPreferences(), "users");
                        refreshLoadEffectDialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }).start();
                return true;
            case R.id.menu_item_choose_user:
                new UsersDialog(this, R.layout.dialog, true).createDialog().show();
                return true;

            case R.id.menu_item_submit_activities_time:
                final AlertDialog submitLoadEffectDialog = new LoadEffectDialog(this, R.layout.dialog, "Отправка результатов...").createDialog();
                submitLoadEffectDialog.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        SettingHandler<FinishedActivity> settingHandler = new SettingHandler<>();
                        List<FinishedActivity> finishedActivitiesCommitted = settingHandler.retrieveSettingList("finishedActivitiesCommitted", FinishedActivity[].class);
                        if (finishedActivitiesCommitted != null) {
                            List<FinishedActivity> errorList = new ArrayList<>();

                            for (FinishedActivity finishedActivity : finishedActivitiesCommitted) {
                                //    @DateTimeFormat(pattern = "dd-MM-yyyy")
                                //    @DateTimeFormat(pattern = "HH:mm:ss")
                                String date = CommonUtil.calendarToString(finishedActivity.getToday());
                                String time = TimerHandler.format(finishedActivity.getTimeInSeconds());

                                Statistic statistic = new Statistic(CurrentUser.getInstance().getUser(), finishedActivity.getActivity(), date, time);
                                try {
                                    new StatisticController().save(statistic);
                                } catch (IOException e) {
                                    errorList.add(finishedActivity);
                                    e.printStackTrace();
                                }
                            }

                            settingHandler.saveSettingList("finishedActivitiesCommitted", null);
                            if (errorList.size() > 0) {
                                settingHandler.saveSettingList("finishedActivitiesCommitted", errorList);
                            }
                        }
                        submitLoadEffectDialog.dismiss();
                    }
                }).start();
                return true;
            case R.id.menu_show_current_activity:
                TimerHandler currentTimerHandler = TimerHandler.getCurrentInstance();
                final AlertDialog dialog = new ActivityInProgressDialog(this, R.layout.dialog, currentTimerHandler, true).createDialog();
                dialog.show();
                return true;
            case R.id.menu_item_close_all_activities:
                TimerHandler timerHandler = TimerHandler.getCurrentInstance();
                if (timerHandler != null) {
                    timerHandler.destroy();
                }

                final AlertDialog loadDialog = new LoadEffectDialog(this, R.layout.dialog, "Остановка активностей...").createDialog();
                loadDialog.show();

                CommonUtil.delayedClose(loadDialog, 1000);


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
