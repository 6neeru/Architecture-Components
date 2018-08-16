package com.example.neeru.architecturecomponent.ui.movie;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.neeru.architecturecomponent.AppExecutors;
import com.example.neeru.architecturecomponent.R;
import com.example.neeru.architecturecomponent.data.remote.model.ResultsBean;
import com.example.neeru.architecturecomponent.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListActivity extends BaseActivity</*ActivityMoviesListBinding,*/ MovieViewModel> implements MovieNavigator {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    /* @BindView(R.id.progressBar)
     ProgressBar progressBar;*/
    //private List<ResultsBean> mResultsBeanList;
    @Inject
    MoviesAdapter moviesAdapter;
    private AppExecutors appExecutors;
    // private CompositeDisposable disposables;
    @Inject
    LinearLayoutManager mLinearLayoutManager;
    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    /*@Inject
    ApiServices apiServices;*/

    @Inject
    LinearLayoutManager mLayoutManager;

    private MovieViewModel mMovieViewModel;
    //private ActivityMoviesListBinding mActivityMovieBinding;


    /*@Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_movies_list;
    }*/

    @Override
    public MovieViewModel getViewModel() {
        mMovieViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MovieViewModel.class);
        return mMovieViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mActivityMovieBinding = getViewDataBinding();
        setContentView(R.layout.activity_movies_list);
        ButterKnife.bind(this);

        mMovieViewModel.setNavigator(this);
        //setUp();
        subscribeToLiveData();

        /*((MVVMApplication) getApplication()).getApiComponent().inject(MoviesListActivity.this);

        disposables = new CompositeDisposable();*/
        appExecutors = new AppExecutors();
        toolbar.setTitle(R.string.title_movie_list);
        setSupportActionBar(toolbar);

        mMovieViewModel.fetchMovies();

        setUpRecyclerView();
      /*  final MovieListViewModel viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.getDataFromServer(disposables, apiServices);
        observeViewModel(viewModel);*/
    }

   /* private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mActivityMovieBinding.recyclerView.setLayoutManager(mLayoutManager);
        mActivityMovieBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mActivityMovieBinding.recyclerView.setAdapter(moviesAdapter);
    }*/

    private void subscribeToLiveData() {
        mMovieViewModel.getMovieListLiveData().observe(this, movies -> mMovieViewModel.addMovieItemsToList(movies));
    }


    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }

    /* private void observeViewModel(MovieListViewModel viewModel) {
        progressBar.setVisibility(View.VISIBLE);
        // Update the list when the data changes
        viewModel.getMovieListObservable().observe(this, listDataWrapper -> {
            progressBar.setVisibility(View.GONE);
            if (listDataWrapper.getData() != null) {
                success(listDataWrapper.getData());
            } else {
                error(listDataWrapper.getError());
            }

        });

    }

    private void error(String error) {
        showAlert(this, error);
    }

    private void success(MovieResponse data) {
        mResultsBeanList = data.getResults();
        final MovieRoomDatabase db = MovieRoomDatabase.getDatabase(getApplicationContext());
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                db.getMovieDao().insertAllMovie(mResultsBeanList);
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        // set the adapter
                        db.getMovieDao().getAll();
                        moviesAdapter = new MoviesAdapter(mResultsBeanList);
                        recyclerView.setAdapter(moviesAdapter);
                    }
                });
            }
        });

    }*/

  /*  @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }*/

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updateMovieList(List<ResultsBean> movieList) {
        moviesAdapter.addItems(movieList);
    }
}
