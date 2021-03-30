package com.example.studentlocations;

public class Child {
    private double lat;
    private double lon;
    int id;

    public Child(double lat, double lon, int id) {
        this.lat = lat;
        this.lon = lon;
        this.id = id;
    }


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
