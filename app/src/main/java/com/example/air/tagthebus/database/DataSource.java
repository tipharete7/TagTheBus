package com.example.air.tagthebus.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.air.tagthebus.model.Picture;

import java.util.ArrayList;
import java.util.List;

import static com.example.air.tagthebus.database.PicturesTable.PICTURES_TABLE;

/**
 * Created by Air on 16/06/2017.
 */

//USE PREPARED STATEMENTS ?
public class DataSource {
    private Context context;
    private SQLiteDatabase database;
    SQLiteOpenHelper dbHelper;
    SQLiteStatement statement;


    public DataSource(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }



    public void addPicture(Picture picture) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PicturesTable.COLUMN_TITLE, picture.getPicture_title());
        contentValues.put(PicturesTable.COLUMN_STATION_ID, picture.getStation_id());
        contentValues.put(PicturesTable.COLUMN_PATH, picture.getPicture_path());
        contentValues.put(PicturesTable.COLUMN_DATE, picture.getPicture_date());
        database.insert(PicturesTable.PICTURES_TABLE, null, contentValues);
        close();
    }

    public void deletePicture(int pictureId){
        open();
        String query =  "DELETE FROM " + PICTURES_TABLE + " WHERE " + PicturesTable.COLUMN_ID + " = " + pictureId;
        statement = database.compileStatement(query);
        statement.executeUpdateDelete();
        close();
    }



    public List<Picture> getPictureByStationId(int stationId) {

        List<Picture> picturesList = new ArrayList<>();
        open();
        String query = "SELECT * FROM " + PICTURES_TABLE + " WHERE " + PicturesTable.COLUMN_STATION_ID + " = " + stationId;
        Cursor cursor =
                database.rawQuery(query,null);

        if (null == cursor)
            return picturesList;
        else
        {
            while (cursor.moveToNext())
            {
                Picture picture = new Picture();
                picture.setPicture_id(cursor.getInt(cursor.getColumnIndex(PicturesTable.COLUMN_ID)));
                picture.setStation_id(cursor.getInt(cursor.getColumnIndex(PicturesTable.COLUMN_STATION_ID)));
                picture.setPicture_title(cursor.getString(cursor.getColumnIndex(PicturesTable.COLUMN_TITLE)));
                picture.setPicture_path(cursor.getString(cursor.getColumnIndex(PicturesTable.COLUMN_PATH)));
                picture.setPicture_date(cursor.getString(cursor.getColumnIndex(PicturesTable.COLUMN_DATE)));
                picturesList.add(picture);
            }
            cursor.close();
            close();
            return picturesList;
        }
    }
}