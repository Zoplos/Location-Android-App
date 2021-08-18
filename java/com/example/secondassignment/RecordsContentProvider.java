package com.example.secondassignment;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RecordsContentProvider extends ContentProvider {
    private UriMatcher uriMatcher;
    private DBHelper dbHelper;
    private static String AUTHORITY = "content://com.example.secondassignment.recordscontentprovider";


    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"/records",1);

        dbHelper = new DBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri result = null;
        switch (uriMatcher.match(uri)){
            case 1:
                RecordsContractor record= new RecordsContractor(values.getAsString("id"),values.getAsString("timestamp"),values.getAsString("longitude"),values.getAsString("latitude"));
                long id = dbHelper.insertRecord(record);
                result = Uri.parse(AUTHORITY+"/records"+id);
                break;
        }
        return result;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
