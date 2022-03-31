/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacetelegrambot;

import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import library2.CsvRecord;
import library2.DatiCondivisi;
import library2.Messaggio;
import library2.Update;
import library2.User;
import library2.DocumentoXML;
import library2.Coordinate;
import library2.Chat;

/**
 *
 * @author gabri
 */
public class ThreadRiceviMessaggio extends Thread{
     HashMap<Long,Long> map;
    public ThreadRiceviMessaggio(){
        
        map = new HashMap<Long,Long>();
    }
    @Override
    public void run() {
        DatiCondivisi dati = new DatiCondivisi();
        File f = new File("dati.csv");
         try {
             List<CsvRecord> lista = dati.leggiCSV(f);
         } catch (IOException ex) {
             Logger.getLogger(ThreadRiceviMessaggio.class.getName()).log(Level.SEVERE, null, ex);
         }
        int size = -2;
        while(true)
        {
            Vector<Update> ArrayUpdates = new Vector<>();
            try {
                ArrayUpdates = dati.getUpdates();
            } catch (IOException ex) {
                Logger.getLogger(ThreadRiceviMessaggio.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(Update element:ArrayUpdates)
            {
                long idUser = Long.parseLong(element.getMessage().getFrom().getId()); //id user
                long idMess = Long.parseLong(element.getMessage().getMessage_id().toString()); //id messaggio
                
                
                if(map.containsValue(idUser) && idMess > map.get(idUser))
                {
                    System.out.println(element.toString());
                    String testo = element.getMessage().getText();

                    if(testo.contains("/citta") && testo.length() > 6) //guardo se è stato digitato citta e dopo c'è qualcosa
                    {
                        String citta = testo.substring(testo.indexOf(" ") + 1); //prendo tutto quello dopo /citta
                        Coordinate coord = null;
               
                        try {
                            coord = dati.getCoordinate(citta); //prendo le coordinate
                        } catch (UnsupportedEncodingException ex) {
                            Logger.getLogger(ThreadRiceviMessaggio.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ThreadRiceviMessaggio.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParserConfigurationException ex) {
                            Logger.getLogger(ThreadRiceviMessaggio.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SAXException ex) {
                            Logger.getLogger(ThreadRiceviMessaggio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            String lat = Float.toString(coord.getLat());
                            String lon = Float.toString(coord.getLon());
                            //utente
                            String idChat = element.getMessage().getChat().getId();
                            String firstName = element.getMessage().getFrom().getFirst_name();
                        try {
                            dati.SalvasuCSV(f); //salvo Csv
                        } catch (IOException ex) {
                            Logger.getLogger(ThreadRiceviMessaggio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            map.put(idUser, idMess);
                     
                    }
                }
                }
            }   
        }
    }

