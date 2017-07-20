package com.udacity.timpl.popmovies.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.udacity.timpl.popmovies.entities.Film;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Chudofom on 18.07.17.
 */
@Dao
public interface FilmDAO {
    @Insert
    void insertFilm(Film film);

    @Delete
    void deleteFilm(Film film);

    @Query("SELECT * FROM films")
    Flowable<List<Film>> getFilms();

    @Query("SELECT * FROM films WHERE id = :filmId LIMIT 1")
    Flowable<List<Film>> getFilm(int filmId);
}
