package com.udacity.timpl.popmovies.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Chudofom on 21.07.17.
 */

public class FilmContract {
    public static final String CONTENT_AUTHORITY = "com.udacity.timpl.popmovies.app";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final class FilmEntry implements BaseColumns {
        // table name
        public static final String TABLE_FILMS = "films";
        // columns
        public static final String _ID = "_id";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_VOTE_COUNT = "vote_count";

        // create content uri
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_FILMS).build();
        // create cursor of base type directory for multiple entries
        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_FILMS;
        // create cursor of base type item for single entry
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_FILMS;

        // for building URIs on insertion
        public static Uri buildFilmUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
