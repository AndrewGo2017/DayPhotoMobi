package ru.sbrf.android.dayphoto.controller.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.sbrf.android.dayphoto.model.Statistic;

public interface StatisticApi {
    @FormUrlEncoded
    @POST("/context/statistic")
    Call<Statistic> save(@Field("activity") String activity, @Field("user") String user, @Field("date") String date, @Field("time") String time);
}
