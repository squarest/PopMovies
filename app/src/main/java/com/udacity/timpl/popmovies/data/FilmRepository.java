package com.udacity.timpl.popmovies.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.udacity.timpl.popmovies.entities.Film;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by Chudofom on 21.07.17.
 */

public class FilmRepository {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public static final FilmRepository instance = new FilmRepository();

    private FilmRepository() {

    }

    public void insertFilm(Film film) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FilmContract.FilmEntry._ID, film.id);
        contentValues.put(FilmContract.FilmEntry.COLUMN_POSTER_PATH, film.posterPath);
        contentValues.put(FilmContract.FilmEntry.COLUMN_RELEASE_DATE, film.releaseDate);
        contentValues.put(FilmContract.FilmEntry.COLUMN_OVERVIEW, film.overview);
        contentValues.put(FilmContract.FilmEntry.COLUMN_ORIGINAL_TITLE, film.originalTitle);
        contentValues.put(FilmContract.FilmEntry.COLUMN_VOTE_COUNT, film.voteCount);
        context.getContentResolver().insert(FilmContract.FilmEntry.CONTENT_URI, contentValues);
    }

    public void deleteFilm(Film film) {
        context.getContentResolver().delete(FilmContract.FilmEntry.buildFilmUri(film.id), null, null);

    }

    public Flowable<List<Film>> getFilms() {
        return Flowable.create(v -> {
            List<Film> films = new ArrayList<>();
            Cursor c =
                    context.getContentResolver().query(FilmContract.FilmEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            null);
            if (c != null) {
                if (c.moveToFirst()) {

                    do {
                        Film film = new Film();
                        film.id = Integer.valueOf(c.getString(c.getColumnIndex(FilmContract.FilmEntry._ID)));
                        film.posterPath = c.getString(c.getColumnIndex(FilmContract.FilmEntry.COLUMN_POSTER_PATH));
                        film.releaseDate = c.getString(c.getColumnIndex(FilmContract.FilmEntry.COLUMN_RELEASE_DATE));
                        film.originalTitle = c.getString(c.getColumnIndex(FilmContract.FilmEntry.COLUMN_ORIGINAL_TITLE));
                        film.overview = c.getString(c.getColumnIndex(FilmContract.FilmEntry.COLUMN_OVERVIEW));
                        film.voteCount = Integer.valueOf(c.getString(c.getColumnIndex(FilmContract.FilmEntry.COLUMN_VOTE_COUNT)));
                        films.add(film);
                    } while (c.moveToNext());
                }
                c.close();
                v.onNext(films);
            } else v.onError(new NullPointerException());
        }, BackpressureStrategy.BUFFER);

    }

    public Flowable<List<Film>> getFilm(int filmId) {
        return Flowable.create(v -> {
            List<Film> films = new ArrayList<>();
            Cursor c =
                    context.getContentResolver().query(FilmContract.FilmEntry.buildFilmUri(filmId),
                            null,
                            null,
                            null,
                            null);
            if (c != null) {
                if (c.moveToFirst()) {
                    Film film = new Film();
                    film.id = Integer.valueOf(c.getString(c.getColumnIndex(FilmContract.FilmEntry._ID)));
                    film.posterPath = c.getString(c.getColumnIndex(FilmContract.FilmEntry.COLUMN_POSTER_PATH));
                    film.releaseDate = c.getString(c.getColumnIndex(FilmContract.FilmEntry.COLUMN_RELEASE_DATE));
                    film.originalTitle = c.getString(c.getColumnIndex(FilmContract.FilmEntry.COLUMN_ORIGINAL_TITLE));
                    film.voteCount = Integer.valueOf(c.getString(c.getColumnIndex(FilmContract.FilmEntry.COLUMN_VOTE_COUNT)));
                    films.add(film);
                }
                c.close();
            }
            v.onNext(films);
        }, BackpressureStrategy.LATEST);
    }

}
