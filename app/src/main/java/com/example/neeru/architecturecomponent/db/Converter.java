package com.example.neeru.architecturecomponent.db;

import android.arch.persistence.room.TypeConverter;

import com.example.neeru.architecturecomponent.data.remote.model.ResultsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;


public class Converter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<ResultsBean> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ResultsBean>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<ResultsBean> someObjects) {
        return gson.toJson(someObjects);
    }

}
