package com.evo.passwordgenerator.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class PasswordContract {

    public static final String CONTENT_AUTHORITY = "com.evo.passwordgenerator";

    // The base of all uris using the content Authority
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // The possible paths
    public static final String PATH_PASSWORD = "password";

    /* Inner class that defines the table contents of the password table */
    public static final class PasswordEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PASSWORD).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PASSWORD;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PASSWORD;

        // Table name
        public static final String TABLE_NAME = "password_table";

        // The password that is saved
        public static final String COLUMN_PASSWORD = "password_column";

        // The time when the password was saved
        public static final String COLUMN_TIME = "time_column";

        public static Uri buildPasswordUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
