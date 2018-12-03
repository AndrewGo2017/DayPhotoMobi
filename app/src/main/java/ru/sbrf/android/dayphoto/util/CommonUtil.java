package ru.sbrf.android.dayphoto.util;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CommonUtil {
    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }

    public static String CalendarToString(Calendar calendar){
        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        String month = Integer.toString(calendar.get(Calendar.MONTH));
        String year = Integer.toString(calendar.get(Calendar.YEAR));

        day = day.length() < 2 ? ("0" + day) : day;
        month = month.length() < 2 ? ("0" + month) : month;

        return day + "-" + month + "-" + year;
    }
}
