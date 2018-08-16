package com.example.neeru.architecturecomponent.rest;

import com.example.neeru.architecturecomponent.data.remote.model.MovieResponse;

import java.util.Observable;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("discover/movie")
    Single<MovieResponse> getMovies(@Query("api_key") String api_key);

}
