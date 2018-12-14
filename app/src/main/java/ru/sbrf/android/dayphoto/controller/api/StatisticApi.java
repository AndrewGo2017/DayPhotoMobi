package ru.sbrf.android.dayphoto.controller.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.sbrf.android.dayphoto.model.Statistic;
import ru.sbrf.android.dayphoto.to.StatisticTo;

public interface StatisticApi {
//    @FormUrlEncoded
    @POST("/context/statistic")
    Call<Void> save(@Body StatisticTo statistic);
}
