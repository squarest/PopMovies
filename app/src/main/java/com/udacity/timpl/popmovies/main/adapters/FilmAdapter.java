package com.udacity.timpl.popmovies.main.adapters;


import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.timpl.popmovies.R;
import com.udacity.timpl.popmovies.main.view.FilmViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by timpl on 09.04.2017.
 */

public class FilmAdapter extends RecyclerView.Adapter<FilmViewHolder> {
    private List<Object> items = new ArrayList<>();

    public FilmAdapter(ArrayList<Object> items) {
        this.items = items;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_film, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Object> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged(); //TODO: diff util
    }
}
