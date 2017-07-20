package com.udacity.timpl.popmovies.detailed.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.timpl.popmovies.R;
import com.udacity.timpl.popmovies.detailed.adapters.TrailerAdapter;
import com.udacity.timpl.popmovies.entities.Film;
import com.udacity.timpl.popmovies.entities.Trailer;
import com.udacity.timpl.popmovies.network.RestClient;

import java.util.ArrayList;

public class DetailedActivity extends AppCompatActivity implements DetailedView {
    public static final String FILM_KEY = "film";

    private Toolbar toolbar;
    private TextView title;
    private ImageView poster;
    private TextView overview;
    private TextView votes;
    private TextView releaseDate;
    private RecyclerView recyclerView;

    private Film film;
    private MenuItem menuItem;
    private DetailedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        film = getIntent().getParcelableExtra(FILM_KEY);
        if (film == null) return;
        initToolbar(film);
        initUi();
        showFilm(film);

        presenter = new DetailedPresenter(this, film);
        presenter.viewInit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_deailed, menu);
        menuItem = menu.findItem(R.id.item_favorite);
        presenter.menuInit();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.menuItemClicked();
        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        title = (TextView) findViewById(R.id.title);
        poster = (ImageView) findViewById(R.id.poster);
        overview = (TextView) findViewById(R.id.overview);
        votes = (TextView) findViewById(R.id.votes);
        releaseDate = (TextView) findViewById(R.id.date);
        recyclerView = (RecyclerView) findViewById(R.id.trailer_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

    @Override
    public void setFavoriteIcon() {
        if (menuItem != null)
            menuItem.setIcon(getResources().getDrawable(R.drawable.ic_favorite));
    }

    @Override
    public void setNoFavoriteIcon() {
        if (menuItem != null)
            menuItem.setIcon(getResources().getDrawable(R.drawable.ic_no_favorite));
    }

    @Override
    public void setTrailers(ArrayList<Trailer> trailers) {
        recyclerView.setAdapter(new TrailerAdapter(trailers));
    }

}
