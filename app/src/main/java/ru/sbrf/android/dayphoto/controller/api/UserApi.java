package ru.sbrf.android.dayphoto.controller.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.sbrf.android.dayphoto.model.Activity;
import ru.sbrf.android.dayphoto.model.User;

public interface UserApi {
    @GET("/context/user")
    Call<List<User>> getAll();
}
