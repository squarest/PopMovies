package com.udacity.timpl.popmovies;

import android.app.Application;

import com.udacity.timpl.popmovies.data.FilmRepository;

/**
 * Created by Chudofom on 18.07.17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FilmRepository.instance.setContext(this);

    }
}
