package com.udacity.timpl.popmovies.main.model.services;

import com.udacity.timpl.popmovies.network.Callback;
import com.udacity.timpl.popmovies.network.RestClient;

/**
 * Created by tplotnikov on 30.06.17.
 */

public class MovieListService implements IMovieListService {
    public static MovieListService instance = new MovieListService();

    private RestClient restClient;
    private MovieListService() {
        this.restClient = RestClient.getInstance();
    }

    @Override
    public void loadTopRated(Callback callback) {
        restClient.loadTopRated(callback);
    }

    @Override
    public void loadPopular(Callback callback) {
        restClient.loadPopular(callback);
    }

    @Override
    public void loadFavorite(Callback callback) {

    }
}