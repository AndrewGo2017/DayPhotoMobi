package ru.sbrf.android.dayphoto;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import ru.sbrf.android.dayphoto.common.TimerHandler;
import ru.sbrf.android.dayphoto.dialog.ActivityInProgressDialog;
import ru.sbrf.android.dayphoto.common.LoadEffectHandler;
import ru.sbrf.android.dayphoto.controller.ActivityGetController;
import ru.sbrf.android.dayphoto.model.Activity;
import ru.sbrf.android.dayphoto.model.ActivityGroup;

public class ActivityDetail extends AppCompatActivity {
    private LoadEffectHandler loadEffectHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("");

        loadEffectHandler = new LoadEffectHandler(getLayoutInflater(), (ViewGroup) findViewById(R.id.layout_root));
        loadEffectHandler.setWindowToLock(getWindow());
        init();
    }

    private void init() {
        final ActivityDetail context = this;

        loadEffectHandler.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final ActivityGroup activityGroup = (ActivityGroup) getIntent().getSerializableExtra("activityGroup");

                final List<Activity> activities = ActivityGetController.getInstance().getAllFromCache(activityGroup.getId());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ViewGroup viewGroupActivity = (ViewGroup)findViewById(R.id.activity_group_linear);
                        for (final Activity activity : activities) {
                            Button activityButton = new Button(context);
                            activityButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(context, activity);
                                }
                            });

                            activityButton.setText(activity.getName());
                            viewGroupActivity.addView(activityButton);
                        }
                        loadEffectHandler.hide();
                        getSupportActionBar().setTitle(activityGroup.getName());
                    }
                });
            }
        }).start();
    }

    private void startActivity(AppCompatActivity context, Activity activity){
        TimerHandler timerHandler = TimerHandler.getNewInstance(activity);
        final AlertDialog dialog = new ActivityInProgressDialog(context, R.layout.dialog, timerHandler, false).createDialog();
        dialog.show();

        try {
            timerHandler.goTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                });
            }
        }).start();
    }

//    private void startActivity(AppCompatActivity context, Activity activity){
//        TimerHandler timerHandler = new TimerHandler();
//        AlertDialog dialog = new ActivityInProgressDialog(context, R.layout.dialog, timerHandler, activity).createDialog();
//        dialog.show();
//        timerHandler.setAlertDialog(dialog);
//
//        try {
//            timerHandler.goTimer();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}