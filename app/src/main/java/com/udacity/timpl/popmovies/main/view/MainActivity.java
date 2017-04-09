package com.udacity.timpl.popmovies.main.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.udacity.timpl.popmovies.R;

public class MainActivity extends AppCompatActivity {

    private static final int SPAN_SIZE = 2;

    private Toolbar toolbar;
    private RecyclerView filmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        filmList = (RecyclerView) findViewById(R.id.list_films);

        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        filmList.setLayoutManager(new GridLayoutManager(this, SPAN_SIZE));
    }
}
