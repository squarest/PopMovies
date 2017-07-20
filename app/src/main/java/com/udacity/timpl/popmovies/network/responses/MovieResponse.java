package com.udacity.timpl.popmovies.network.responses;


import com.udacity.timpl.popmovies.entities.Film;

import java.util.ArrayList;

/**
 * Created by tplotnikov on 13.04.17.
 */

public class MovieResponse {
    public int page = 1;
    public ArrayList<Film> results = new ArrayList<>();
}
