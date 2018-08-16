package com.example.neeru.architecturecomponent.data;

import com.example.neeru.architecturecomponent.data.remote.model.MovieResponse;

import io.reactivex.Observable;

public interface DbHelper {
    Observable<MovieResponse> getMovieResponse();
}
