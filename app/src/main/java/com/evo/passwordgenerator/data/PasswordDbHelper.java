package com.evo.passwordgenerator.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.evo.passwordgenerator.data.PasswordContract.PasswordEntry;

public class PasswordDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "password.db";

    public PasswordDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_PASSWORD_TABLE = "CREATE TABLE " + PasswordEntry.TABLE_NAME + " (" +
                PasswordEntry._ID + " INTEGER PRIMARY KEY," +
                PasswordEntry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                PasswordEntry.COLUMN_TIME + " INTEGER NOT NULL, " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_PASSWORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Write some code here to change the sql scheme when a version is incremented
        // For now i'm just going to delete the database and recreate it
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PasswordEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
