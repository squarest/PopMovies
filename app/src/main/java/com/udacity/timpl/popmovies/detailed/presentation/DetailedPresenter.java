package com.udacity.timpl.popmovies.detailed.presentation;

import com.udacity.timpl.popmovies.detailed.domain.DetailedInteract;
import com.udacity.timpl.popmovies.detailed.domain.IDetailedInteract;
import com.udacity.timpl.popmovies.entities.Film;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Chudofom on 20.07.17.
 */

public class DetailedPresenter {
    private boolean isFavorite;
    private DetailedView view;
    private IDetailedInteract detailedInteract;
    private Film film;

    public DetailedPresenter(DetailedView view, Film film) {
        this.view = view;
        this.detailedInteract = DetailedInteract.instance;
        this.film = film;
    }

    public void viewInit() {
        detailedInteract.loadTrailers(film.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> view.setTrailers(response.results),
                        Throwable::printStackTrace);
    }

    public void menuInit() {
        detailedInteract.checkFavorite(film.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean ->
                {
                    if (aBoolean) view.setFavoriteIcon();
                    else view.setNoFavoriteIcon();
                }, Throwable::fillInStackTrace);

    }

    public void menuItemClicked() {
        detailedInteract.changefavorite(film)
                .subscribe(aBoolean -> {
                    if (aBoolean)
                        view.setFavoriteIcon();
                    else view.setNoFavoriteIcon();
                }, Throwable::printStackTrace);
    }

}
