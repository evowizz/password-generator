package com.evo.passwordgenerator.activities;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.evo.passwordgenerator.R;
import com.evo.passwordgenerator.data.PasswordContract;
import com.evo.passwordgenerator.items.SaveItem;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

public class SaveActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int LOADER_ID = 100;

    public static final String[] SAVE_COLUMNS = {
            PasswordContract.PasswordEntry._ID,
            PasswordContract.PasswordEntry.COLUMN_PASSWORD,
    };
    public static final int COL_ID = 0;
    public static final int COL_PASSWORD = 1;

    FastItemAdapter fastItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.save_recyclerview);

        fastItemAdapter = new FastItemAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(fastItemAdapter);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = PasswordContract.PasswordEntry.CONTENT_URI;

        return new CursorLoader(this,
                uri,
                SAVE_COLUMNS,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
        if (c!= null) {
            fastItemAdapter.clear();
            for (int i = 0; i < c.getCount(); i++) {
                c.moveToPosition(i);
                fastItemAdapter.add(new SaveItem(c.getLong(COL_ID), c.getString(COL_PASSWORD)));
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}
