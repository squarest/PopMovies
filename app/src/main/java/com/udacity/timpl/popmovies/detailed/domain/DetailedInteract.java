package com.udacity.timpl.popmovies.detailed.domain;


import com.udacity.timpl.popmovies.data.FilmRepository;
import com.udacity.timpl.popmovies.entities.Film;
import com.udacity.timpl.popmovies.network.API;
import com.udacity.timpl.popmovies.network.RestClient;
import com.udacity.timpl.popmovies.network.responses.TrailerResponse;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tplotnikov on 30.06.17.
 */

public class DetailedInteract implements IDetailedInteract {
    public static DetailedInteract instance = new DetailedInteract();

    private boolean isFavorite;

    private FilmRepository database;
    private API api;

    public DetailedInteract() {
        this.database = FilmRepository.instance;
        this.api = RestClient.getInstance().getApi();
    }

    @Override
    public Single<TrailerResponse> loadTrailers(int id) {
        return api.getMovieTrailers(String.valueOf(id))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<Boolean> checkFavorite(int filmId) {
        return database.getFilm(filmId)
                .subscribeOn(Schedulers.io())
                .map(films -> {
                    isFavorite = films.size() == 1;
                    return isFavorite;
                });
    }

    @Override
    public Single<Boolean> changefavorite(Film film) {
        return Single.create(singleEmitter ->
        {
            if (isFavorite) {
                this.database.deleteFilm(film);

            } else {
                this.database.insertFilm(film);
            }
            isFavorite = !isFavorite;
            singleEmitter.onSuccess(isFavorite);
        });
    }
}
