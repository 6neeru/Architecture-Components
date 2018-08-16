package com.example.neeru.architecturecomponent.network_calls;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.neeru.architecturecomponent.data.remote.model.MovieResponse;
import com.example.neeru.architecturecomponent.data_wrapper.DataWrapper;
import com.example.neeru.architecturecomponent.rest.ApiServices;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ApiServices apiService;
    private CompositeDisposable disposables;

    public MovieRepository(CompositeDisposable disposables, ApiServices apiService) {
        this.apiService = apiService;//ApiClient.getClient().create(ApiServices.class);
        this.disposables = disposables;
    }

    public LiveData<DataWrapper<MovieResponse>> getMovieListFromServer() {
        final MutableLiveData<DataWrapper<MovieResponse>> data = new MutableLiveData<>();


      /*  disposables.add(apiService.getMovies("004cbaf19212094e32aa9ef6f6577f22")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());*/

        apiService.getMovies("004cbaf19212094e32aa9ef6f6577f22").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                data.setValue(new DataWrapper<>(response.body(), null));
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                data.setValue(new DataWrapper<MovieResponse>(null, t.getMessage()));
            }

        });
        return data;

    }

    public void onResponse(MutableLiveData<DataWrapper<MovieResponse>> data, MovieResponse MovieResponse) {
        data.setValue(new DataWrapper<>(MovieResponse, null));
    }

    public void onFailure(MutableLiveData<DataWrapper<MovieResponse>> data, @NonNull Throwable t) {
        data.setValue(new DataWrapper<MovieResponse>(null, t.getMessage()));
    }


}
