package com.udacity.timpl.popmovies.main.model.services;

import com.udacity.timpl.popmovies.network.Callback;

/**
 * Created by tplotnikov on 30.06.17.
 */

public interface IMovieListService {
    void loadTopRated(Callback callback);
    void loadPopular(Callback callback);
    void loadFavorite(Callback callback);
}
