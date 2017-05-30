package com.udacity.timpl.popmovies.network;

/**
 * Created by tplotnikov on 13.04.17.
 */

public interface Callback<T> {
    void onSuccess(T result);
    void onError(Throwable e);
}
