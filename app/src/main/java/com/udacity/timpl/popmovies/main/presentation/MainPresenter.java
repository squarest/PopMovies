package com.udacity.timpl.popmovies.main.presentation;

import com.udacity.timpl.popmovies.R;
import com.udacity.timpl.popmovies.entities.Film;
import com.udacity.timpl.popmovies.main.domain.IMainInteract;
import com.udacity.timpl.popmovies.main.domain.MainInteract;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Chudofom on 20.07.17.
 */

public class MainPresenter {
    private MainView mainView;
    private IMainInteract mainInteract = MainInteract.instance;

    public MainPresenter(MainView view) {
        this.mainView = view;
    }

    public void viewInit() {
        mainInteract.loadPopular()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieResponse -> mainView.showFilms(movieResponse.results),
                        throwable -> mainView.showError(throwable));;
    }

    public void menuItemSelected(int menuItemId) {
        switch (menuItemId) {
            case R.id.menu_popular:
                mainInteract.loadPopular()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(movieResponse -> mainView.showFilms(movieResponse.results),
                                throwable -> mainView.showError(throwable));
                break;
            case R.id.menu_top_rated:
                mainInteract.loadTopRated()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(movieResponse -> mainView.showFilms(movieResponse.results),
                                throwable -> mainView.showError(throwable));
                break;
            case R.id.menu_favorite:
                mainInteract.loadFavorite()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(films -> mainView.showFilms((ArrayList<Film>) films),
                                throwable -> mainView.showError(throwable));
                break;
            default:
                break;
        }
    }
}
