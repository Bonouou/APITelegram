/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library2;

/**
 *
 * @author gabri
 */
public class Coordinate {
    Float lat,lon;

    public Coordinate(Float lat, Float lon) {
        this.lat = lat;
        this.lon = lon;
    }
    public Coordinate(){
    
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }
}
