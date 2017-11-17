package com.example.air.tagthebus.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by Air on 16/06/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_FILE_NAME = "tagTheBus.db";
    public static final int DB_VERSION = 3;
    SQLiteStatement statement;

    public DbHelper(Context context) {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        statement = db.compileStatement(PicturesTable.SQL_CREATE);
        statement.execute();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        statement = db.compileStatement(PicturesTable.SQL_DELETE);
        statement.execute();
        onCreate(db);
    }
}
