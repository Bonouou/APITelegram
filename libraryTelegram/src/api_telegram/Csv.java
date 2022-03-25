/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_telegram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author bonuglia_gabriele
 */
public class Csv {
  String firstName, idChat, lat, lon;

    public Csv(String idChat, String firstName, String lat, String lon) {
        this.idChat = idChat;
        this.firstName = firstName;
        this.lat = lat;
        this.lon = lon;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
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
    
    public String ToString()
    {
        return idChat + ";" + firstName + ";" + lat + ";" + lon;
    }
}
