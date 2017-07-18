package com.udacity.timpl.popmovies.main.view;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.udacity.timpl.popmovies.R;
import com.udacity.timpl.popmovies.detailed.DetailedActivity;
import com.udacity.timpl.popmovies.main.model.entities.Film;
import com.udacity.timpl.popmovies.network.RestClient;

/**
 * Created by timpl on 09.04.2017.
 */

public class FilmViewHolder extends RecyclerView.ViewHolder {

    private ImageView poster;
    private Film film;

    public FilmViewHolder(final View itemView) {
        super(itemView);
        poster = (ImageView) itemView.findViewById(R.id.poster);

        itemView.setOnClickListener(v -> {
            if (film == null) return;

            Intent intent = new Intent(itemView.getContext(), DetailedActivity.class);
            intent.putExtra(DetailedActivity.FILM_KEY, film);
            itemView.getContext().startActivity(intent);
        });
    }

    public void bind(Film film) {
        this.film = film;
        Picasso.with(itemView.getContext())
                .load(RestClient.IMG_BASE_URL + film.posterPath)
                .resize(itemView.getMinimumWidth(), 0)
                .noFade()
                .into(poster);
    }

    public void onViewDetached() {
        Picasso.with(itemView.getContext())
                .cancelRequest(poster);
    }
}
