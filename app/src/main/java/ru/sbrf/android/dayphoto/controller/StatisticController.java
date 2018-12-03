package ru.sbrf.android.dayphoto.controller;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import ru.sbrf.android.dayphoto.controller.api.StatisticApi;
import ru.sbrf.android.dayphoto.model.Statistic;

public class StatisticController extends BaseController {
    public void save(Statistic statistic) throws IOException {
        StatisticApi statisticApi = retrofit.create(StatisticApi.class);
        Call<Statistic> save = statisticApi.save(Long.toString(statistic.getActivity().getId()), Long.toString(statistic.getUser().getId()), statistic.getDate(), statistic.getTime());
        save.execute();
    }
}
