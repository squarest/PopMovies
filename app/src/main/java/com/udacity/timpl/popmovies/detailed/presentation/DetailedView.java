package com.udacity.timpl.popmovies.detailed.presentation;

import com.udacity.timpl.popmovies.entities.Trailer;

import java.util.ArrayList;

/**
 * Created by Chudofom on 20.07.17.
 */

public interface DetailedView {
    void setFavoriteIcon();
    void setNoFavoriteIcon();
    void setTrailers(ArrayList<Trailer> trailers);
}
