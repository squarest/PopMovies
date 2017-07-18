package com.udacity.timpl.popmovies.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tplotnikov on 25.05.17.
 */

public interface API {

    @GET("movie/popular")
    Single<MovieResponse> getPopularMovies();

    @GET("movie/top_rated")
    Single<MovieResponse> getTopRatedMovies();

    @GET("movie/{id}/videos")
    Single<Object> getMovieTrailers(@Path("id") String movieId);

    @GET("movie/{id}/reviews")
    Single<Object> getMovieReviews(@Path("id") String movieId);
}
