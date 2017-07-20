package com.udacity.timpl.popmovies.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by timpl on 09.04.2017.
 */

@Entity(tableName = "films")
public class Film implements Parcelable {
    @PrimaryKey
    @SerializedName("id")
    public int id;

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

    public Film() {
    }

    private Film(Parcel source) {
        this.id = source.readInt();
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
        dest.writeInt(id);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeString(originalTitle);
        dest.writeString(overview);
        dest.writeInt(voteCount);
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
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
