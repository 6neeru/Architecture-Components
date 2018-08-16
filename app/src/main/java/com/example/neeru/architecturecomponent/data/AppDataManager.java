package com.example.neeru.architecturecomponent.data;


import android.content.Context;

import com.example.neeru.architecturecomponent.data.remote.ApiHelper;
import com.example.neeru.architecturecomponent.data.remote.model.MovieResponse;
import com.google.gson.Gson;

import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final DbHelper mDbHelper;

    private final Gson mGson;

    public AppDataManager(ApiHelper mApiHelper, Context mContext, DbHelper mDbHelper, Gson mGson) {
        this.mApiHelper = mApiHelper;
        this.mContext = mContext;
        this.mDbHelper = mDbHelper;
        this.mGson = mGson;
    }


    @Override
    public Observable<MovieResponse> getMovieList() {
        return mApiHelper.getMovieList();
    }
}
