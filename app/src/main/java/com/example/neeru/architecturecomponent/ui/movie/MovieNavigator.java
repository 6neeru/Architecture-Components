package com.example.neeru.architecturecomponent.ui.movie;

import com.example.neeru.architecturecomponent.data.remote.model.ResultsBean;

import java.util.List;

public interface MovieNavigator {
    void handleError(Throwable throwable);

    void updateMovieList(List<ResultsBean> movieList);

}
