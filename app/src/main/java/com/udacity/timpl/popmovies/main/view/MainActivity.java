package com.udacity.timpl.popmovies.main.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.timpl.popmovies.R;
import com.udacity.timpl.popmovies.main.adapters.FilmAdapter;
import com.udacity.timpl.popmovies.main.model.entities.Film;
import com.udacity.timpl.popmovies.main.model.services.IMovieListService;
import com.udacity.timpl.popmovies.main.model.services.MovieListService;
import com.udacity.timpl.popmovies.network.Callback;
import com.udacity.timpl.popmovies.network.MovieResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int SPAN_SIZE = 2;

    private Toolbar toolbar;
    private RecyclerView filmList;

    private IMovieListService movieListService = MovieListService.instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();

        loadPopular();
    }

    @Override
    protected void onStart() {
        super.onStart();
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                SPAN_SIZE = 3;
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                SPAN_SIZE = 2;
                break;

        }
        filmList.setLayoutManager(new GridLayoutManager(this, SPAN_SIZE));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_popular:
                loadPopular();
                break;
            case R.id.menu_top_rated:
                loadTopRated();
                break;
            case R.id.menu_favorite:
                loadFavorite();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        filmList = (RecyclerView) findViewById(R.id.list_films);

        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        filmList.setLayoutManager(new GridLayoutManager(this, SPAN_SIZE));
    }

    private void loadPopular() {
        movieListService.loadPopular(networkCallback);
    }

    private void loadTopRated() {
        movieListService.loadTopRated(networkCallback);
    }

    private void loadFavorite() {
        movieListService.loadFavorite(dbCallback);
    }

    private Callback<MovieResponse> networkCallback = new Callback<MovieResponse>() {
        @Override
        public void onSuccess(MovieResponse result) {
            showSuccess(result.results);
        }

        @Override
        public void onError(Throwable e) {
            showError(e);
        }
    };
    private Callback<List<Film>> dbCallback = new Callback<List<Film>>() {
        @Override
        public void onSuccess(List<Film> films) {
            showSuccess((ArrayList<Film>) films);
        }

        @Override
        public void onError(Throwable e) {
            showError(e);
        }
    };

    private void showSuccess(ArrayList<Film> films) {
        if (filmList.getAdapter() == null) {
            filmList.setAdapter(new FilmAdapter(films));
        } else {
            ((FilmAdapter) filmList.getAdapter()).setItems(films);
        }
    }

    private void showError(Throwable e) {
        e.printStackTrace();
        Toast.makeText(this, R.string.dump_error_message, Toast.LENGTH_LONG).show();
    }
}
