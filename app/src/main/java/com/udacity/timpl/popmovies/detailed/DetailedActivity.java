package com.udacity.timpl.popmovies.detailed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.timpl.popmovies.App;
import com.udacity.timpl.popmovies.R;
import com.udacity.timpl.popmovies.db.FilmDAO;
import com.udacity.timpl.popmovies.main.model.entities.Film;
import com.udacity.timpl.popmovies.network.RestClient;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailedActivity extends AppCompatActivity {
    public static final String FILM_KEY = "film";

    private Toolbar toolbar;
    private TextView title;
    private ImageView poster;
    private TextView overview;
    private TextView votes;
    private TextView releaseDate;
    private FilmDAO database;
    private Film film;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        film = getIntent().getParcelableExtra(FILM_KEY);
        if (film == null) return;
        database = App.database.filmDAO();
        initToolbar(film);
        initUi();
        showFilm(film);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_deailed, menu);
        MenuItem item = menu.findItem(R.id.item_favorite);
        database.getFilm(film.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(films -> {
                    boolean favorite = !films.isEmpty();
                    if (favorite)
                        item.setIcon(getResources().getDrawable(R.drawable.ic_favorite));
                    else item.setIcon(getResources().getDrawable(R.drawable.ic_no_favorite));
                    return favorite;
                })
                .subscribe(aBoolean -> isFavorite = aBoolean, Throwable::fillInStackTrace);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Single.create(singleEmitter ->
        {
            if (isFavorite) {
                this.database.deleteFilm(film);
            } else {
                this.database.insertFilm(film);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(a -> {
                    if (isFavorite)
                        item.setIcon(getResources().getDrawable(R.drawable.ic_no_favorite));
                    else item.setIcon(getResources().getDrawable(R.drawable.ic_favorite));
                }, error -> {
                    Log.d("Error", error.getMessage());
                });
        return super.onOptionsItemSelected(item);
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
