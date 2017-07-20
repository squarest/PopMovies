package com.udacity.timpl.popmovies.detailed.domain;

import com.udacity.timpl.popmovies.entities.Film;
import com.udacity.timpl.popmovies.network.responses.TrailerResponse;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by tplotnikov on 30.06.17.
 */

public interface IDetailedInteract {
    Single<TrailerResponse> loadTrailers(int id);

    Flowable<Boolean> checkFavorite(int id);

    Single<Boolean> changefavorite(Film film);
}
