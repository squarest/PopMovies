package com.udacity.timpl.popmovies.main.presentation;

import com.udacity.timpl.popmovies.entities.Film;

import java.util.ArrayList;

/**
 * Created by Chudofom on 20.07.17.
 */

public interface MainView {
    void showFilms(ArrayList<Film> films);

    void showError(Throwable e);
}
