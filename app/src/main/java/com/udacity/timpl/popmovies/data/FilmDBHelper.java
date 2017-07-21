package com.udacity.timpl.popmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Chudofom on 21.07.17.
 */

public class FilmDBHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = FilmDBHelper.class.getSimpleName();

    //name & version
    private static final String DATABASE_NAME = "Film.db";
    private static final int DATABASE_VERSION = 12;

    public FilmDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create the database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " +
                FilmContract.FilmEntry.TABLE_FILMS + "(" + FilmContract.FilmEntry._ID +
                " INTEGER PRIMARY KEY, " +
                FilmContract.FilmEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
                FilmContract.FilmEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                FilmContract.FilmEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +
                FilmContract.FilmEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                FilmContract.FilmEntry.COLUMN_VOTE_COUNT + " INTEGER NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    // Upgrade database when version is changed.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to " +
                newVersion + ". OLD DATA WILL BE DESTROYED");
        // Drop the table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FilmContract.FilmEntry.TABLE_FILMS);
        sqLiteDatabase.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +
                FilmContract.FilmEntry.TABLE_FILMS + "'");

        // re-create database
        onCreate(sqLiteDatabase);
    }
}
