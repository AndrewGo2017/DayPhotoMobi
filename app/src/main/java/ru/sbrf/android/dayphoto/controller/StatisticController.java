package ru.sbrf.android.dayphoto.controller;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import ru.sbrf.android.dayphoto.controller.api.StatisticApi;
import ru.sbrf.android.dayphoto.model.Statistic;
import ru.sbrf.android.dayphoto.to.StatisticTo;

public class StatisticController extends BaseController {
    public void save(Statistic statistic) throws IOException {
        StatisticApi statisticApi = retrofit.create(StatisticApi.class);
        StatisticTo statisticTo = StatisticTo.getTo(statistic);
        Call<Void> save = statisticApi.save(statisticTo);

        save.execute();
    }
}
