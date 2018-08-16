package com.example.neeru.architecturecomponent.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.neeru.architecturecomponent.data.remote.model.MovieResponse;
import com.example.neeru.architecturecomponent.data_wrapper.DataWrapper;
import com.example.neeru.architecturecomponent.network_calls.MovieRepository;
import com.example.neeru.architecturecomponent.rest.ApiServices;

import io.reactivex.disposables.CompositeDisposable;

public class MovieListViewModel extends AndroidViewModel {
    private LiveData<DataWrapper<MovieResponse>> movieListObservable;

    public MovieListViewModel(Application application) {
        super(application);
    }

    public void getDataFromServer(CompositeDisposable disposables,ApiServices apiService) {
        movieListObservable = new MovieRepository(disposables,apiService).getMovieListFromServer();
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<DataWrapper<MovieResponse>> getMovieListObservable() {
        return movieListObservable;
    }

}
