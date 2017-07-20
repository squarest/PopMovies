package com.udacity.timpl.popmovies.main.domain;

import com.udacity.timpl.popmovies.App;
import com.udacity.timpl.popmovies.entities.Film;
import com.udacity.timpl.popmovies.network.API;
import com.udacity.timpl.popmovies.network.responses.MovieResponse;
import com.udacity.timpl.popmovies.network.RestClient;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Chudofom on 19.07.17.
 */

public class MainInteract implements IMainInteract {
    public static MainInteract instance = new MainInteract();

    private API api;

    private MainInteract() {
        this.api = RestClient.getInstance().getApi();
    }

    @Override
    public Single<MovieResponse> loadTopRated() {
        return api.getTopRatedMovies()
                .subscribeOn(Schedulers.io());

    }


    @Override
    public Single<MovieResponse> loadPopular() {
        return api.getPopularMovies()
                .subscribeOn(Schedulers.io());

    }


    @Override
    public Flowable<List<Film>> loadFavorite() {
        return App.database.filmDAO().getFilms()
                .subscribeOn(Schedulers.io());

    }
}
