package ru.sbrf.android.dayphoto.controller;

import android.content.SharedPreferences;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ru.sbrf.android.dayphoto.common.SettingHandler;
import ru.sbrf.android.dayphoto.model.BaseOuterEntity;

abstract class BaseGetController<T extends BaseOuterEntity> extends BaseController {

    protected void saveToSharedPreferences (SharedPreferences sharedPreferences, List<T> entities, String name) {
        SettingHandler<T> settingHandler = new SettingHandler<>(sharedPreferences);
        settingHandler.saveSettingList(name, entities);
    }

    public void refresh(SharedPreferences sharedPreferences, String name){
        List<T> activityGroups = getAll();
        saveToSharedPreferences(sharedPreferences, activityGroups, name);
        setAllCached(activityGroups);
    }

    protected void cacheFromSharedPreferences (SharedPreferences sharedPreferences, Class<T[]> clazz, String name) {
        SettingHandler<T> settingHandler = new SettingHandler<>(sharedPreferences);
        List<T> list = settingHandler.retrieveSettingList(name, clazz);
        if (list != null){
            if (list.size() != 0){
                setAllCached(list);
            }
        } else {
            refresh(sharedPreferences, name);
        }
    }

    protected List<T> getAll( Call<List<T>> call) {
        Response<List<T>> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<T> body = response != null ? response.body() : null;
        return body;
    }

    protected abstract void setAllCached(List<T> activityGroups);

    protected abstract List<T> getAll();

}
