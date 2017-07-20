package com.udacity.timpl.popmovies.main.domain;

import com.udacity.timpl.popmovies.entities.Film;
import com.udacity.timpl.popmovies.network.responses.MovieResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by tplotnikov on 30.06.17.
 */

public interface IMainInteract {
    Single<MovieResponse> loadTopRated();

    Single<MovieResponse> loadPopular();

    Flowable<List<Film>> loadFavorite();

}
