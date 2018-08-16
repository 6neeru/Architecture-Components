package com.example.neeru.architecturecomponent.di.builder;

import com.example.neeru.architecturecomponent.ui.movie.MovieModule;
import com.example.neeru.architecturecomponent.ui.movie.MoviesListActivity;
import com.example.neeru.architecturecomponent.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MovieModule.class)
    abstract MoviesListActivity bindMoviesListActivity();
}
