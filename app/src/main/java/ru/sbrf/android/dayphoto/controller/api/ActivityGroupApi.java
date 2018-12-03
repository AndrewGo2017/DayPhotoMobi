package ru.sbrf.android.dayphoto.controller.api;

import ru.sbrf.android.dayphoto.model.ActivityGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ActivityGroupApi  {
    @GET("/context/activityGroup/")
    Call<List<ActivityGroup>> getAll();
}
