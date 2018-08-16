package com.example.neeru.architecturecomponent.rest;

import com.example.neeru.architecturecomponent.data.remote.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("discover/movie")
    //Observable<MovieResponse> getMovies(@Query("api_key") String api_key);
    Call<MovieResponse> getMovies(@Query("api_key") String api_key);

}
