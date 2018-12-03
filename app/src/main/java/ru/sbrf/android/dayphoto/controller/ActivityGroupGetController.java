package ru.sbrf.android.dayphoto.controller;

import android.content.SharedPreferences;

import ru.sbrf.android.dayphoto.controller.api.ActivityGroupApi;
import ru.sbrf.android.dayphoto.model.ActivityGroup;

import java.util.List;

import retrofit2.Call;

public class ActivityGroupGetController extends BaseGetController<ActivityGroup> {
    private static volatile ActivityGroupGetController instance;

    private List<ActivityGroup> allCached;

    private ActivityGroupGetController(){

    }

    public static ActivityGroupGetController getInstance() {
        if (instance == null) {
            synchronized (ActivityGroupGetController.class) {
                if (instance == null) {
                    instance = new ActivityGroupGetController();
                }
            }
        }
        return instance;
    }

    public List<ActivityGroup> getAllFromCache() {
        if (allCached == null){
            allCached = getAll();
        }

        return allCached;
    }

    public List<ActivityGroup> getAll() {
        ActivityGroupApi activityGroupApi = retrofit.create(ActivityGroupApi.class);
        Call<List<ActivityGroup>> user = activityGroupApi.getAll();
        List all = super.getAll(user);
        return all;
    }

    public void cacheFromSharedPreferences (SharedPreferences sharedPreferences, String name){
        super.cacheFromSharedPreferences(sharedPreferences, ActivityGroup[].class, name);
    }

    @Override
    protected void setAllCached(List<ActivityGroup> activityGroups) {
        this.allCached = activityGroups;
    }
}
