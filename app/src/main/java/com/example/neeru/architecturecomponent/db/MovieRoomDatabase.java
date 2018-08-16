package com.example.neeru.architecturecomponent.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.neeru.architecturecomponent.data.remote.model.ResultsBean;

@Database(entities = {ResultsBean.class}, version = 1, exportSchema = false)
/*@TypeConverters({Converter.class})*/
public abstract class MovieRoomDatabase extends RoomDatabase {
    public abstract MovieDao getMovieDao();

    private static MovieRoomDatabase INSTANCE;

    public static MovieRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieRoomDatabase.class, "movie_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}