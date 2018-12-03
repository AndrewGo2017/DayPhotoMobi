package ru.sbrf.android.dayphoto.common;

import ru.sbrf.android.dayphoto.model.User;

public class CurrentUser {
    private static volatile CurrentUser instance;

    private User user;

    private CurrentUser(){

    }

    public static CurrentUser getInstance(){
       if (instance == null){
           synchronized (CurrentUser.class){
                if (instance == null){
                    instance = new CurrentUser();
                }
           }
       }
       return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
         this.user = user;
    }
}
