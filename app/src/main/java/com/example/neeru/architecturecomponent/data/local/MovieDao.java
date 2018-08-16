package com.example.neeru.architecturecomponent.data.local;

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
    List<ResultsBean> getAll();

    @Query("DELETE FROM movie_table")
    void deleteAll();
}
