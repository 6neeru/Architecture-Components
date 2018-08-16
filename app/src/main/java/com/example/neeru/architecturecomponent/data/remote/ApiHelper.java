package com.example.neeru.architecturecomponent.data.remote;

import com.example.neeru.architecturecomponent.data.remote.model.MovieResponse;

import io.reactivex.Observable;

public interface ApiHelper {
    Observable<MovieResponse> getMovieList();

}
