package ru.sbrf.android.dayphoto.common;

import android.content.SharedPreferences;

public class Preferences {
    private static volatile Preferences instance;

    private SharedPreferences sharedPreferences;

    private Preferences(){

    }

    public static Preferences getInstance(){
       if (instance == null){
           synchronized (Preferences.class){
                if (instance == null){
                    instance = new Preferences();
                }
           }
       }
       return instance;
    }

    public SharedPreferences getPreferences() {
        return sharedPreferences;
    }

    public void setPreferences(SharedPreferences sharedPreferences) {
         this.sharedPreferences = sharedPreferences;
    }
}
