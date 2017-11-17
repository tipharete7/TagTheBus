package com.example.air.tagthebus.model;

/**
 * Created by Air on 16/06/2017.
 */

public class Picture {

    public Picture(){}

    private int picture_id;
    private int station_id;
    private String picture_path;
    private String picture_title;
    private String picture_date;

    public int getPicture_id() { return picture_id; }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public int getStation_id() { return station_id; }

    public void setStation_id(int station_id) { this.station_id = station_id; }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public String getPicture_title() {
        return picture_title;
    }

    public void setPicture_title(String picture_title) {
        this.picture_title = picture_title;
    }

    public String getPicture_date() {
        return picture_date;
    }

    public void setPicture_date(String picture_date) {
        this.picture_date = picture_date;
    }



}
