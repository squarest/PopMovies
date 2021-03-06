package com.udacity.timpl.popmovies.main.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by timpl on 09.04.2017.
 */

public class Film implements Parcelable {

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("overview")
    public String overview;

    @SerializedName("vote_count")
    public int voteCount;

    public Film() {}

    private Film(Parcel source) {
        this.posterPath = source.readString();
        this.releaseDate = source.readString();
        this.originalTitle = source.readString();
        this.overview = source.readString();
        this.voteCount = source.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeString(originalTitle);
        dest.writeString(overview);
        dest.writeInt(voteCount);
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel source) {
            return new Film(source);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
