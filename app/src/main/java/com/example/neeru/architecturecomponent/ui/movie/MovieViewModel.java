package com.example.neeru.architecturecomponent.ui.movie;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;

import com.example.neeru.architecturecomponent.data.remote.model.MovieResponse;
import com.example.neeru.architecturecomponent.data.remote.model.ResultsBean;
import com.example.neeru.architecturecomponent.rest.ApiClient;
import com.example.neeru.architecturecomponent.rest.ApiServices;
import com.example.neeru.architecturecomponent.ui.base.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends BaseViewModel<MovieNavigator> {

    public final List<ResultsBean> movieObservableArrayList = new ObservableArrayList<>();
    private final MutableLiveData<List<ResultsBean>> movieListLiveData;
    private final ApiServices apiService;


    public MovieViewModel() {
        movieListLiveData = new MutableLiveData<>();
        apiService = ApiClient.getClient().create(ApiServices.class);
        fetchMovies();
    }

    public void addMovieItemsToList(List<ResultsBean> movies) {
        movieObservableArrayList.clear();
        movieObservableArrayList.addAll(movies);
    }

    public void fetchMovies() {
        //setIsLoading(true);
        apiService.getMovies("004cbaf19212094e32aa9ef6f6577f22").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> movieResponse) {
                if (movieResponse.body() != null) {
                    movieListLiveData.setValue(movieResponse.body().getResults());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                getNavigator().handleError(t);
            }

        });

       /* getCompositeDisposable().add(getDataManager()
                .getMovieList()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(movieResponse -> {
                    if (movieResponse != null && movieResponse.getResults() != null) {
                        movieListLiveData.setValue(movieResponse.getResults());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));*/
    }

    public MutableLiveData<List<ResultsBean>> getMovieListLiveData() {
        return movieListLiveData;
    }

    public List<ResultsBean> getMovieObservableList() {
        return movieObservableArrayList;
    }

}
