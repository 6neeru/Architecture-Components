package com.example.neeru.architecturecomponent.ui.movie;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.neeru.architecturecomponent.data.DataManager;
import com.example.neeru.architecturecomponent.data.remote.model.ResultsBean;
import com.example.neeru.architecturecomponent.ui.base.BaseViewModel;
import com.example.neeru.architecturecomponent.utils.rx.SchedulerProvider;

import java.util.List;

public class MovieViewModel extends BaseViewModel<MovieNavigator> {

    public final ObservableList<ResultsBean> movieObservableArrayList = new ObservableArrayList<>();

    private final MutableLiveData<List<ResultsBean>> movieListLiveData;

    public MovieViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        movieListLiveData = new MutableLiveData<>();
        fetchMovies();
    }

    public void addMovieItemsToList(List<ResultsBean> movies) {
        movieObservableArrayList.clear();
        movieObservableArrayList.addAll(movies);
    }

    public void fetchMovies() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
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
                }));
    }

    public MutableLiveData<List<ResultsBean>> getMovieListLiveData() {
        return movieListLiveData;
    }

    public ObservableList<ResultsBean> getMovieObservableList() {
        return movieObservableArrayList;
    }

}
