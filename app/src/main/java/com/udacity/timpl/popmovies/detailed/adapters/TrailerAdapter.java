package com.udacity.timpl.popmovies.detailed.adapters;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.timpl.popmovies.entities.Trailer;

import java.util.ArrayList;

/**
 * Created by Chudofom on 19.07.17.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.SimpleViewHolder> {
    private ArrayList<Trailer> trailers;

    public TrailerAdapter(ArrayList<Trailer> trailers) {
        this.trailers = trailers;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = new TextView(parent.getContext());
        return new SimpleViewHolder(view);
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.textView.setText(trailers.get(position).name);
        holder.textView.setOnClickListener(v ->
        {
            String key = trailers.get(position).key;
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key));
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + key));
            try {
                holder.textView.getContext().startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                holder.textView.getContext().startActivity(webIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }
}