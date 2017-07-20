package com.udacity.timpl.popmovies.main.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.timpl.popmovies.R;
import com.udacity.timpl.popmovies.entities.Film;
import com.udacity.timpl.popmovies.main.presentation.FilmViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timpl on 09.04.2017.
 */

public class FilmAdapter extends RecyclerView.Adapter<FilmViewHolder> {
    private List<Film> items = new ArrayList<>();

    public FilmAdapter(ArrayList<Film> items) {
        this.items = items;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_film, parent, false);
        view.setMinimumWidth(parent.getWidth() / 2);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public void onViewDetachedFromWindow(FilmViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onViewDetached();
    }

    @Override
    public void onViewRecycled(FilmViewHolder holder) {
        super.onViewRecycled(holder);
        holder.onViewDetached();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Film> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
