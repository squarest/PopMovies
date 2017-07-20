package com.udacity.timpl.popmovies.network;

import com.udacity.timpl.popmovies.BuildConfig;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tplotnikov on 10.04.17.
 */

public class RestClient {

    /* Singleton init */
    private static RestClient instance = new RestClient();

    public static RestClient getInstance() {
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

    public API getApi() {
        return api;
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


    }

//    @Override
//    public void loadTrailers(int id, Callback callback) {
//        api.getMovieTrailers(String.valueOf(id))
//                .subscribeOn(Schedulers.io());
//    }

