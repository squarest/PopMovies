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
    private RestClient() {}

    public static final String IMG_BASE_URL = "http://image.tmdb.org/t/p/w185";
    public static final String IMG_ORIGIN_URL = "http://image.tmdb.org/t/p/w500";

    private static final String BASE_URL = "http://api.themoviedb.org/3";
    private static final String API_KEY = BuildConfig.API_KEY;

    static {
        assert API_KEY != "";
    }


    @Override
    public void loadPopular(Callback callback) {
        new NetRequest(callback).execute("/movie/popular");
    }

    @Override
    public void loadTopRated(Callback callback) {
        new NetRequest(callback).execute("/movie/top_rated");
    }

    private class NetRequest extends AsyncTask<String, Integer, Object> {

        private Callback callback;

        public NetRequest(Callback callback) {
            this.callback = callback;
        }

        private final int READ_TIMEOUT = 30 * 1000; // 30 seconds

        private BufferedReader reader;
        private Gson gson = new GsonBuilder().create();
        @Override
        protected Object doInBackground(String... params) {
            if (params.length < 1)
                return null;
            URL url;
            try {
                url = new URL(String.format("%s%s?api_key=%s", BASE_URL, params[0], API_KEY));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setReadTimeout(READ_TIMEOUT);
                connection.connect();

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder buffer = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                return gson.fromJson(buffer.toString(), MovieResponse.class);

            } catch (MalformedURLException me) {
                return me;
            } catch (IOException ie) {
                return ie;
            } catch (JsonSyntaxException je) {
                return je;
            }
        }

        @Override
        protected void onPostExecute(Object response) {
            super.onPostExecute(response);
            if (callback == null) return;

            if (response == null)
                response = new NullPointerException("Server response is null");

            if (response instanceof Exception) {
                callback.onError((Exception) response);
            } else {
                callback.onSuccess(response);
            }
        }
    }
}
