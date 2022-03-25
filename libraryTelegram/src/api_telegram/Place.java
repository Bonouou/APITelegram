/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_telegram;

/**
 *
 * @author bonuglia_gabriele
 */
public class Place {
    float lat, lon;

    public Place(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    Place() {
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
