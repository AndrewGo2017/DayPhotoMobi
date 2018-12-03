package ru.sbrf.android.dayphoto.controller;

import android.content.SharedPreferences;

import java.util.List;

import retrofit2.Call;
import ru.sbrf.android.dayphoto.controller.api.UserApi;
import ru.sbrf.android.dayphoto.model.User;

public class UserGetController extends BaseGetController<User> {
    private static volatile UserGetController instance;

    private List<User> allCached;

    private UserGetController(){

    }

    public static UserGetController getInstance() {
        if (instance == null) {
            synchronized (UserGetController.class) {
                if (instance == null) {
                    instance = new UserGetController();
                }
            }
        }
        return instance;
    }

    public List<User> getAllFromCache() {
        if (allCached == null){
            allCached = getAll();
        }

        return allCached;
    }

    public List<User> getAll() {
        UserApi userApi = retrofit.create(UserApi.class);
        Call<List<User>> user = userApi.getAll();
        List all = super.getAll(user);
        return all;
    }

    public void cacheFromSharedPreferences (SharedPreferences sharedPreferences, String name){
        super.cacheFromSharedPreferences(sharedPreferences, User[].class, name);
    }

    @Override
    protected void setAllCached(List<User> users) {
        this.allCached = users;
    }
}
