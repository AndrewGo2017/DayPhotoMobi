package ru.sbrf.android.dayphoto.controller;

import android.content.SharedPreferences;

import ru.sbrf.android.dayphoto.controller.api.ActivityApi;
import ru.sbrf.android.dayphoto.model.Activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ActivityGetController extends BaseGetController<Activity> {
    private static volatile ActivityGetController instance;

    private List<Activity> allCached;

    private ActivityGetController() {
    }

    public static ActivityGetController getInstance() {
        if (instance == null) {
            synchronized (ActivityGetController.class) {
                if (instance == null) {
                    instance = new ActivityGetController();
                }
            }
        }
        return instance;
    }

    public List<Activity> getAllFromCache(long activityGroup) {
        if (allCached == null){
            allCached = getAll();
        }

        return getActivitiesByGroupId(allCached, activityGroup);
    }

    public void cacheFromSharedPreferences (SharedPreferences sharedPreferences, String name){
        super.cacheFromSharedPreferences(sharedPreferences, Activity[].class, name);
    }

    public List<Activity> getAllByActivityGroup(long activityGroup) {
        ActivityApi activityApi = retrofit.create(ActivityApi.class);
        Call<List<Activity>> user = activityApi.getAllWithActivityGroup(activityGroup);
        Response<List<Activity>> activityGroupResponse = null;
        try {
            activityGroupResponse = user.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Activity> body = activityGroupResponse != null ? activityGroupResponse.body() : null;
        return body;
    }

    public List<Activity> getAll() {
        ActivityApi activityApi = retrofit.create(ActivityApi.class);
        Call<List<Activity>> user = activityApi.getAll();
        Response<List<Activity>> activityGroupResponse = null;
        try {
            activityGroupResponse = user.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Activity> body = activityGroupResponse != null ? activityGroupResponse.body() : null;
        return body;
    }

    public List<Activity> getAll(long activityGroup) {
        List<Activity> body = getAll();
        return getActivitiesByGroupId(body, activityGroup);
    }

    private List<Activity> getActivitiesByGroupId(List<Activity> activities, long id){
        if (activities == null) return null;

        List<Activity> filtered = new ArrayList<>();
        for (Activity activity : activities){
            if (activity.getActivityGroup().getId() == id){
                filtered.add(activity);
            }
        }

        return filtered;
    }

    @Override
    protected void setAllCached(List<Activity> activities) {
        this.allCached = activities;
    }
}