package com.example.neeru.architecturecomponent.ui.movie;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.neeru.architecturecomponent.R;
import com.example.neeru.architecturecomponent.data.local.MovieRoomDatabase;
import com.example.neeru.architecturecomponent.data.remote.model.ResultsBean;
import com.example.neeru.architecturecomponent.ui.base.BaseActivity;
import com.example.neeru.architecturecomponent.utils.AppExecutors;
import com.example.neeru.architecturecomponent.utils.Helper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListActivity extends BaseActivity<MovieViewModel> implements MovieNavigator {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private List<ResultsBean> mResultsBeanList;
    private MoviesAdapter moviesAdapter;
    private AppExecutors appExecutors;
    ViewModelProvider.Factory mViewModelFactory;

    private MovieViewModel mMovieViewModel;

    @Override
    public MovieViewModel getViewModel() {
        mMovieViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MovieViewModel.class);
        return mMovieViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        ButterKnife.bind(this);
        setToolbarTitle();
        init();
        setUpRecyclerView();
        subscribeToLiveData();
        mMovieViewModel.setNavigator(this);
        progressBar.setVisibility(View.VISIBLE);
        mMovieViewModel.fetchMovies();

    }

    private void init() {
        appExecutors = new AppExecutors();
        mMovieViewModel = new MovieViewModel();
    }

    private void setToolbarTitle() {
        toolbar.setTitle(R.string.title_movie_list);
        setSupportActionBar(toolbar);
    }

    private void subscribeToLiveData() {
        mMovieViewModel.getMovieListLiveData().observe(this, movies -> {
            mMovieViewModel.addMovieItemsToList(movies);
            mResultsBeanList = mMovieViewModel.getMovieObservableList();
            saveDataIntoDatabase();
        });
    }

    private void saveDataIntoDatabase() {
        final MovieRoomDatabase db = MovieRoomDatabase.getDatabase(getApplicationContext());
        appExecutors.diskIO().execute(() -> {
            db.getMovieDao().deleteAll();
            db.getMovieDao().insertAllMovie(mResultsBeanList);
            mResultsBeanList = db.getMovieDao().getAll();
            appExecutors.mainThread().execute(() -> {
                // set the adapter
                moviesAdapter = new MoviesAdapter(mResultsBeanList);
                recyclerView.setAdapter(moviesAdapter);
                progressBar.setVisibility(View.GONE);
            });
        });
    }


    private void setUpRecyclerView() {
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);

    }

    @Override
    public void handleError(Throwable throwable) {
        progressBar.setVisibility(View.GONE);
        Helper.showAlert(this, throwable.getMessage());
    }

}
