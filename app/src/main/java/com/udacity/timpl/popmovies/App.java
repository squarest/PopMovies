package com.udacity.timpl.popmovies;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.udacity.timpl.popmovies.db.FilmDB;

/**
 * Created by Chudofom on 18.07.17.
 */

public class App extends Application {
    public  static FilmDB database;
    @Override
    public void onCreate() {
        super.onCreate();
        database= Room.databaseBuilder(this, FilmDB.class, "data").build();
    }
}
