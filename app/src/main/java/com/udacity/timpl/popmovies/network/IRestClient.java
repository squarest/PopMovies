package com.udacity.timpl.popmovies.network;

/**
 * Created by tplotnikov on 10.04.17.
 */

public interface IRestClient {
    void loadPopular(Callback callback);
    void loadTopRated(Callback callback);
}
