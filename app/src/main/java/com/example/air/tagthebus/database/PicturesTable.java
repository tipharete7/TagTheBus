package com.example.air.tagthebus.database;

/**
 * Created by Air on 16/06/2017.
 */

public class PicturesTable {

    public static final String PICTURES_TABLE = "pictures";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "pictureTitle";
    public static final String COLUMN_PATH = "picturePath";
    public static final String COLUMN_DATE = "pictureDate";
    public static final String COLUMN_STATION_ID = "stationId";

    public static final String SQL_CREATE =
            "CREATE TABLE " + PICTURES_TABLE + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_STATION_ID + " INTEGER NOT NULL, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_PATH + " TEXT, " +
                    COLUMN_DATE + " TEXT " + ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + PICTURES_TABLE;
}
