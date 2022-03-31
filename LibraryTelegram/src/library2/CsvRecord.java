/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library2;

/**
 *
 * @author gabri
 */
public class CsvRecord {
   String firstName, idChat, lat, lon;
    public CsvRecord(String firstName, String idChat, String lat, String lon) {
        this.firstName = firstName;
        this.idChat = idChat;
        this.lat = lat;
        this.lon = lon;
    }
    public CsvRecord(){
    
    
    }
    @Override
    public String toString() {
        return "CsvRecord{" + "firstName=" + firstName + ", idChat=" + idChat + ", lat=" + lat + ", lon=" + lon + '}';
    }
   

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getIdChat() {
        return idChat;
    }

    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
