package com.example.neeru.architecturecomponent.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.neeru.architecturecomponent.data.remote.model.ResultsBean;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMovie(List<ResultsBean> resultsBeanList);

    @Query("SELECT * FROM movie_table")
    LiveData<List<ResultsBean>> getAll();

}
