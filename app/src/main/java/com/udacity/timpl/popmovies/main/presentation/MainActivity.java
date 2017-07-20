package com.udacity.timpl.popmovies.main.presentation;

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
import com.udacity.timpl.popmovies.entities.Film;
import com.udacity.timpl.popmovies.main.adapters.FilmAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView {

    private static int SPAN_SIZE = 2;

    private RecyclerView filmList;
    private MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        initUi();
        presenter.viewInit();
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
        presenter.menuItemSelected(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        filmList = (RecyclerView) findViewById(R.id.list_films);
        filmList.setLayoutManager(new GridLayoutManager(this, SPAN_SIZE));
    }

    @Override
    public void showError(Throwable e) {
        e.printStackTrace();
        Toast.makeText(this, R.string.dump_error_message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFilms(ArrayList<Film> films) {
        if (filmList.getAdapter() == null) {
            filmList.setAdapter(new FilmAdapter(films));
        } else {
            ((FilmAdapter) filmList.getAdapter()).setItems(films);
        }
    }
}
