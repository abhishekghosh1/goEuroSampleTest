package com.goEuro.test.dto;

/**
 * Position Class
 * contains geo position with latitude and longitude
 * <p/>
 * Created by Abhishek Ghosh on 23/08/14.
 */
public class Position {
    private double latitude;

    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
