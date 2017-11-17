package com.example.air.tagthebus.model;

/**
 * Created by Air on 15/06/2017.
 */

public class Station {


    public Station(){}


    private String station_id;
    private String street_name;
    private String city;
    private String utm_x ;
    private String utm_y ;
    private String latitude ;
    private String longitude ;
    private String furniture;
    private String buses;
    private String distance;


    public String getStation_id() { return station_id; }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUtm_x() {
        return utm_x;
    }

    public void setUtm_x(String utm_x) {
        this.utm_x = utm_x;
    }

    public String getUtm_y() {
        return utm_y;
    }

    public void setUtm_y(String utm_y) {
        this.utm_y = utm_y;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getBuses() {
        return buses;
    }

    public void setBuses(String buses) {
        this.buses = buses;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}





