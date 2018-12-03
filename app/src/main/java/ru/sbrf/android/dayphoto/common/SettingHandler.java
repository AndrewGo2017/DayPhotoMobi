package ru.sbrf.android.dayphoto.common;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import ru.sbrf.android.dayphoto.model.BaseEntity;

public class SettingHandler <T extends BaseEntity> {
    private SharedPreferences preferences;

    public SettingHandler(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public SettingHandler() {
        this.preferences = Preferences.getInstance().getPreferences();
    }

    public void saveSetting(String name, T obj){
        save(name, obj);
    }

    public void saveSettingList(String name, List<T> objList){
        save(name, objList);
    }

    private void save(String name, Object entities){
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(entities);
        editor.putString(name, json);
        editor.apply();
    }

    public List<T> retrieveSettingList(String name, Class<T[]> clazz){
        String json = retrieve(name);
        if (json != null){
            return stringToArray(json, clazz);
        }
        return null;
    }

    public T retrieveSetting(String name, Class<T> clazz){
        String json = retrieve(name);
        if (json != null){
            return stringToEntity(json, clazz);
        }
        return null;
    }

    private String retrieve(String name){
        String json = preferences.getString(name, "");
        if (json.equals("null") || json.trim().equals("")) {
            return null;
        }
        return json;
    }

    private List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr);
    }

    private T stringToEntity(String s, Class<T> clazz) {
        T entity = new Gson().fromJson(s, clazz);
        return entity;
    }
}
