package com.udacity.timpl.popmovies.network;

import com.udacity.timpl.popmovies.main.model.entities.Film;

/**
 * Created by tplotnikov on 10.04.17.
 */

public interface IRestClient {
    void loadPopular(Callback callback);
    void loadTopRated(Callback callback);
}
