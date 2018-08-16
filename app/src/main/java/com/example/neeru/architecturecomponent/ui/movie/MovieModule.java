package com.example.neeru.architecturecomponent.ui.movie;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.neeru.architecturecomponent.utils.ViewModelProviderFactory;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieModule {

   /* @Provides
    MovieViewModel movieViewModel(DataManager dataManager,
                                  SchedulerProvider schedulerProvider) {
        return new MovieViewModel(dataManager, schedulerProvider);
    }
*/
    @Provides
    LinearLayoutManager providesLinearLayoutManager(MoviesListActivity moviesListActivity) {
        return new LinearLayoutManager(moviesListActivity);
    }


    @Provides
    MoviesAdapter provideMoviesAdapter() {
        return new MoviesAdapter(new ArrayList<>());
    }

    @Provides
    ViewModelProvider.Factory provideMovieViewModel(MovieViewModel movieViewModel) {
        return new ViewModelProviderFactory<>(movieViewModel);
    }

}
