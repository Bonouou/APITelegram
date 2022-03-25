/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacetelegrambot;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import org.json.*;
import org.xml.sax.SAXException;
import libraryTelegram.Csv;
import libraryTelegram.Functions;
import libraryTelegram.Message;
import libraryTelegram.Update;
import libraryTelegram.User;
import libraryTelegram.parseXml;
import libraryTelegram.test;
/**
 *
 * @author bonuglia_gabriele
 */
public class InterfaceTelegramBot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Functions f = new Functions();
        Csv newCsv = new Csv();
        String scelta = "0";
        do
        {
            System.out.println("\nScelta:");
            System.out.println("[1]GetMe\n[2]GetUpdates\n[3]SendMessage\n[4]GetCoordinate");

            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            scelta = myObj.nextLine();

            switch(scelta)
            {
                case "1":
                    User user = f.getMe();
                    System.out.println(user.ToString());
                    break;
                case "2":
                    Vector<Update> ArrayUpdates = f.getUpdates();
                    for(Update element:ArrayUpdates)
                    {
                        System.out.println(element.ToString());
                    }
                    break;
                case "3":
                    System.out.println("\nChat id:");
                    String idChat = myObj.nextLine();
                    System.out.println("");
                    
                    System.out.println("\ntesto:");
                    String text = myObj.nextLine();
                    System.out.println("");
                    
                    Message mess = f.sendMessage(idChat,text);
                    System.out.println(mess.ToString()); 
                    break;
                case "4":
                    Vector<Update> ArrayMsg = f.getUpdates();
                    for(Update msgs:ArrayMsg)
                    {
                        //System.out.println(msgs.ToString());
                        String testo = msgs.getMessage().getText();
                        Integer idChat2 = msgs.getMessage().getChat().getId();
                        String nome = msgs.getMessage().getChat().getFirst_name();
                        String coord = "";
                        if(testo.contains("/citta") && testo.length() > 6){
                            String citta = testo.substring(testo.indexOf(" "));
                            System.out.println(citta);
                            coord = f.getCoordinate(citta);
                            
                             
                        }
                        
                        newCsv.toCsv(idChat2,nome,coord);
                        
                    }
                    break;
            }
        }
        while(!scelta.equals("-1"));
    }
    }
    
}
