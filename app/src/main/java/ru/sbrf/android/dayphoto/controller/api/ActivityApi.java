package ru.sbrf.android.dayphoto.controller.api;

import ru.sbrf.android.dayphoto.model.Activity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ActivityApi {
    @GET("/context/activity/activityGroup/{id}")
    Call<List<Activity>> getAllWithActivityGroup(@Path("id") long id);

    @GET("/context/activity")
    Call<List<Activity>> getAll();
}
