/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

/**
 *
 * @author gabri
 */
public class DatiCondivisi {

    User u = new User();
    //

    public User getMe() throws MalformedURLException, IOException {
        URL url = new URL("https://api.telegram.org/bot5162106433:AAEb-qfASfYxa3c4jhLmDOztTVIYG-L-jR8/getMe");
        String inline = readUrl(url);
        JSONObject obj = new JSONObject(inline);
        JSONObject res = (JSONObject) obj.get("result"); // result
        String id = res.get("id").toString();
        Boolean is_bot = res.getBoolean("is_bot");
        String first_name = res.getString("first_name");
        String username = res.getString("username");
        Boolean can_join_groups = res.getBoolean("can_join_groups");
        Boolean can_read_all_group_messages = res.getBoolean("can_read_all_group_messages");
        Boolean supports_inline_queries = res.getBoolean("supports_inline_queries");

        u = new User(id, is_bot, can_join_groups, can_read_all_group_messages, supports_inline_queries, first_name, username);
        return u;
    }

    public Vector<Update> getUpdates() throws MalformedURLException, IOException {
        Vector<Update> VettoreUpdate = new Vector<Update>();
        URL url = new URL("https://api.telegram.org/bot5162106433:AAEb-qfASfYxa3c4jhLmDOztTVIYG-L-jR8/getUpdates");
        String inline = readUrl(url);
        JSONObject obj = new JSONObject(inline);
        JSONArray res = (JSONArray) obj.get("result");
        for (int i = 0; i < res.length(); i++) {
            JSONObject obj2 = res.getJSONObject(i);
            Integer update_id = obj2.getInt("update_id");

            // parsing del json messaggio
            JSONObject objMessaggio = obj2.getJSONObject("message");
            Integer message_id = objMessaggio.getInt("message_id");
            Integer date = objMessaggio.getInt("date");
            String text = objMessaggio.getString("text");

            // parsing del from ( cioè l'utente )
            JSONObject objfrom = objMessaggio.getJSONObject("from");
            String first_name = objfrom.getString("first_name");
            String username = objfrom.getString("username");
            String id = objfrom.get("id").toString();
            Boolean is_bot = objfrom.getBoolean("is_bot");
            Boolean can_join_groups = objfrom.getBoolean("can_join_groups");
            Boolean can_read_all_group_messages = objfrom.getBoolean("can_read_all_group_messages");
            Boolean supports_inline_queries = objfrom.getBoolean("supports_inline_queries");

            User u = new User(id, is_bot, can_join_groups, can_read_all_group_messages, supports_inline_queries, first_name, username);

            // parsing della chat
            JSONObject objChat = objMessaggio.getJSONObject("chat");
            String idchat = objChat.get("id").toString();
            String first_nameChat = objChat.getString("first_name");
            String usernameChat = objChat.getString("username");
            String type = objChat.getString("type");

            Chat c = new Chat(idchat, type, first_nameChat);
            Messaggio m = new Messaggio(message_id, u, date, c, text);

            VettoreUpdate.add(new Update(update_id, m));
        }
        return VettoreUpdate;
    }

    //metodo per mandare un messaggio
    public Messaggio mandaMessaggio(String chat_id, String testo) throws IOException {
        Messaggio m = new Messaggio();
        URL url = new URL("https://api.telegram.org/bot5162106433:AAEb-qfASfYxa3c4jhLmDOztTVIYG-L-jR8/sendMessage?chat_id=" + chat_id + "&text=" + testo);
        String inline = readUrl(url);
        JSONObject obj = new JSONObject(inline);
        JSONObject res = (JSONObject) obj.get("result");
        Integer message_id = res.getInt("message_id");
        return m;
    }

    private String readUrl(URL url) throws IOException { // leggere l'url

        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }
        scanner.close();
        return inline;
    }

    public void SalvasuCSV(File f) throws IOException {
        List<CsvRecord> listacsv = leggiCSV(f);
        FileWriter fw;
        fw = new FileWriter(f);
            BufferedWriter bf = new BufferedWriter(fw); 
            for(int j = 0; j < listacsv.size() - 1; j++)
            {
                bf.write(listacsv.get(j).toString() + "\n");
            }
            bf.write(listacsv.get(listacsv.size() - 1).toString());
            bf.flush();
            bf.close();
    }

    public List<CsvRecord> leggiCSV(File f) throws FileNotFoundException, IOException {
        List<CsvRecord> lista = new ArrayList<CsvRecord>();
        FileReader fr;

        fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        while (linea != null) {
            String[] d = linea.split(";");
            lista.add(new CsvRecord(d[0], d[1], d[2], d[3]));
            linea = br.readLine();
        };

        return lista;
    }
    
    public Coordinate getCoordinate(String citta) throws MalformedURLException, UnsupportedEncodingException, IOException, ParserConfigurationException, SAXException{
    String value = URLEncoder.encode(citta, StandardCharsets.UTF_8.toString());
        URL url = new URL("https://nominatim.openstreetmap.org/search?q=" + value + "&format=xml&addressdetails=1");
        String result = new BufferedReader(new InputStreamReader(url.openStream())).lines().collect(Collectors.joining("\n"));
        DocumentoXML doc = new DocumentoXML();
        Coordinate place = doc.GetCoordinate(url.toString());
        return place;
    }
            
    public double getDistances(Coordinate p1, Coordinate p2){
      return Math.acos(Math.cos(Math.toRadians(90-p1.lat))*Math.cos(Math.toRadians(90-p2.lat))+Math.sin(Math.toRadians(90-p1.lat))*Math.sin(Math.toRadians(90-p2.lat))*Math.cos(Math.toRadians(p1.lon-p2.lon)))*6371*1000;
    }        
}
