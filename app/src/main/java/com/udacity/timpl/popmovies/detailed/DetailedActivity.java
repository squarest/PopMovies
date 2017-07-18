package com.udacity.timpl.popmovies.detailed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.timpl.popmovies.R;
import com.udacity.timpl.popmovies.main.model.entities.Film;
import com.udacity.timpl.popmovies.network.RestClient;

public class DetailedActivity extends AppCompatActivity {
    public static final String FILM_KEY = "film";

    private Toolbar toolbar;
    private TextView title;
    private ImageView poster;
    private TextView overview;
    private TextView votes;
    private TextView releaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        Film film = getIntent().getParcelableExtra(FILM_KEY);
        if (film == null) return;

        initToolbar(film);
        initUi();
        showFilm(film);
    }

    private void initUi() {
        title = (TextView) findViewById(R.id.title);
        poster = (ImageView) findViewById(R.id.poster);
        overview = (TextView) findViewById(R.id.overview);
        votes = (TextView) findViewById(R.id.votes);
        releaseDate = (TextView) findViewById(R.id.date);
    }

    private void initToolbar(Film film) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(film.originalTitle);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showFilm(Film film) {
        title.setText(film.originalTitle);
        overview.setText(film.overview);
        votes.setText(String.format(getString(R.string.vote_count), film.voteCount));
        releaseDate.setText(film.releaseDate);

        Picasso.with(this)
                .load(RestClient.IMG_ORIGIN_URL + film.posterPath)
                .into(poster);
    }
}
