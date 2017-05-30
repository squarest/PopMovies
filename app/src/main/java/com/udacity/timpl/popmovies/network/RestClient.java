package com.udacity.timpl.popmovies.network;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.udacity.timpl.popmovies.BuildConfig;
import com.udacity.timpl.popmovies.main.model.entities.Film;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tplotnikov on 10.04.17.
 */

public class RestClient implements IRestClient {

    /* Singleton init */
    private static RestClient instance;

    public static RestClient getInstance() {
        if (instance == null)
            instance = new RestClient();
        return instance;
    }

    public static final String IMG_BASE_URL = "http://image.tmdb.org/t/p/w185";

    public static final String IMG_ORIGIN_URL = "http://image.tmdb.org/t/p/w500";
    private static final String BASE_URL = "http://api.themoviedb.org/3/";

    private static final String API_KEY = BuildConfig.API_KEY;
    private API api;

    private RestClient() {
        api = create();
    }


    private API create() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    HttpUrl url = chain.request().url().newBuilder()
                            .addQueryParameter("api_key", API_KEY)
                            .build();
                    Request req = chain.request().newBuilder()
                            .url(url)
                            .build();
                    return chain.proceed(req);
                }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(API.class);
    }

    @Override
    public void loadPopular(Callback callback) {
        api.getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, callback::onError);
    }

    @Override
    public void loadTopRated(Callback callback) {
        api.getTopRatedMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, callback::onError);
    }
}
