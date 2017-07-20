package com.udacity.timpl.popmovies.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.udacity.timpl.popmovies.entities.Film;


/**
 * Created by Chudofom on 18.07.17.
 */
@Database(entities = {Film.class}, version = 1)
public  abstract class FilmDB extends RoomDatabase {
    public abstract FilmDAO filmDAO();
}
