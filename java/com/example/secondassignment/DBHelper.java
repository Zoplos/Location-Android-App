package com.example.secondassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static String DBNAME= "SecondAssignment";
    public static String TABLE_NAME = "RECORDS";
    public static String FIELD_0 = "ID";
    public static String FIELD_1 = "UNIX_TIMESTAMP";
    public static String FIELD_2 = "LONGITUDE";
    public static String FIELD_3 = "LATITUDE";

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+FIELD_0+" INTEGER PRIMARY KEY AUTOINCREMENT,"+FIELD_1+" VARCHAR(20), "+FIELD_2+" FLOAT, "+FIELD_3+" FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertRecord(RecordsContractor record){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_1, record.getUnix_timestamp());
        values.put(FIELD_2, record.getLongitude());
        values.put(FIELD_3, record.getLatitude());
        long id = database.insert(TABLE_NAME,null,values);
        return id;
    }
}
