package com.udacity.timpl.popmovies.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chudofom on 19.07.17.
 */

public class Trailer {
    @SerializedName("key")
    public String key;
    @SerializedName("name")
    public String name;
    @SerializedName("site")
    public String site;
}
