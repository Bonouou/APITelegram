/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;
/**
 *
 * @author bonuglia_gabriele
 */

// 
public class Functions {
     public User getMe() throws MalformedURLException, IOException
    {
    User user = new User();
    
    URL url = new URL("https://api.telegram.org/5162106433:AAEb-qfASfYxa3c4jhLmDOztTVIYG-L-jR8/getMe");
    String inline = readUrl(url); //get contenuto
        JSONObject obj = new JSONObject(inline); //lo trasformo in JSONObject
        JSONObject res = (JSONObject) obj.get("result"); //prendo l'oggetto result
      Integer id = res.getInt("id");
        Boolean is_bot = res.getBoolean("is_bot");
        //Opzionali
        Boolean can_join_groups = null;
        if(res.has("can_join_groups")) 
            can_join_groups = res.getBoolean("can_join_groups");
        
        Boolean can_read_all_group_messages = null;
        if(res.has("can_read_all_group_messages")) 
            can_read_all_group_messages = res.getBoolean("can_read_all_group_messages");
        
        Boolean supports_inline_queries = null;
        if(res.has("can_read_all_group_messages")) 
            supports_inline_queries = res.getBoolean("supports_inline_queries");
       
        String first_name = res.getString("first_name");
        
        String last_name = "NULL";
        if(res.has("last_name")) 
            last_name = res.getString("last_name");
        
        String username = "NULL";
        if(res.has("username")) 
            username = res.getString("username");
            
        String language_code = "NULL";
        if(res.has("language_code")) 
            language_code = res.getString("language_code");
        
        user = new User(id, is_bot, can_join_groups, can_read_all_group_messages, supports_inline_queries, first_name, last_name, username, language_code);
         return user;
         
         
    }
     
     public Vector<Update> getUpdates() throws MalformedURLException, IOException{
         Vector<Update> ArrayUpdates = new Vector<Update>();
            URL url = new URL("https://api.telegram.org/5162106433:AAEb-qfASfYxa3c4jhLmDOztTVIYG-L-jR8/getUpdates");
          String inline = readUrl(url);
        JSONObject obj = new JSONObject(inline);
        JSONArray res = (JSONArray) obj.get("result"); //prendo array result
         
         for(int i = 0; i < res.length(); i++)
        {
            JSONObject obj2 = res.getJSONObject(i); //prendo l'oggetto del array result
            
            Integer update_id = obj2.getInt("update_id");
            
            //Oggetto messagge
            JSONObject objMess = (JSONObject) obj2.get("message"); //JSONObject messagge
            Integer message_id = objMess.getInt("message_id");
            Integer date = objMess.getInt("date");
            String text = objMess.getString("text");

            //Oggetto from 
            JSONObject Objfrom = (JSONObject) objMess.get("from"); //JSONObject from
            String first_name = Objfrom.getString("first_name");
            Integer id = Objfrom.getInt("id");
            Boolean is_bot = Objfrom.getBoolean("is_bot");
            //Opzionali
            Boolean can_join_groups = null;
            if(Objfrom.has("can_join_groups")) 
                can_join_groups = Objfrom.getBoolean("can_join_groups");

            Boolean can_read_all_group_messages = null;
            if(Objfrom.has("can_read_all_group_messages")) 
                can_read_all_group_messages = Objfrom.getBoolean("can_read_all_group_messages");

            Boolean supports_inline_queries = null;
            if(Objfrom.has("can_read_all_group_messages")) 
                supports_inline_queries = Objfrom.getBoolean("supports_inline_queries");

            String last_name = "NULL";
            if(Objfrom.has("last_name")) 
                last_name = Objfrom.getString("last_name");

            String username = "NULL";
            if(Objfrom.has("username"))
                username = Objfrom.getString("username");

            String language_code = "NULL";
            if(Objfrom.has("language_code")) 
                language_code = Objfrom.getString("language_code");
            
            //
            User user = new User(id, is_bot, can_join_groups, can_read_all_group_messages, supports_inline_queries, first_name, last_name, username, language_code);

            //Oggetto Chat che andrà dentro Message
            JSONObject objChat = (JSONObject) objMess.get("chat"); //prendo l'oggetto chat da Message
            Integer idchat = objChat.getInt("id");
            
            String first_nameChat = "NULL";
            if(objChat.has("first_name")) //attributo opzionale 
                first_nameChat = objChat.getString("first_name");
            
            String type = objChat.getString("type");
            
            //costruttore chat che andrà dentro Message
            Chat chat = new Chat(idchat, type, first_nameChat);
            
            Messaggio mess = new Messaggio(message_id, user, date, chat, text);
            
            ArrayUpdates.add(new Update(update_id, mess));
        }
     return ArrayUpdates;
     }
     
    public Messaggio sendMessage(String chat_id,String testo) throws MalformedURLException, IOException{
        Messaggio mess = new Messaggio();
    URL url = new URL("https://api.telegram.org/5162106433:AAEb-qfASfYxa3c4jhLmDOztTVIYG-L-jR8/sendMessage?chat_id=" + chat_id + "&text=" + testo);
    String inline = readUrl(url);
        
        //Using the JSON simple library parse the string into a json object
        JSONObject obj = new JSONObject(inline);
        JSONObject res = (JSONObject) obj.get("result"); //prendo l'object JSON result
        
        Integer message_id = res.getInt("message_id"); // input message_id
        
        JSONObject objJsonFrom = (JSONObject) res.get("from"); //prendo l'object JSON 'from' da result
        String first_name = objJsonFrom.getString("first_name");
        Integer id = objJsonFrom.getInt("id");
        Boolean is_bot = objJsonFrom.getBoolean("is_bot");
        // Opzionali
        Boolean can_join_groups = null;
        if(objJsonFrom.has("can_join_groups")) 
            can_join_groups = objJsonFrom.getBoolean("can_join_groups");

        Boolean can_read_all_group_messages = null;
        if(objJsonFrom.has("can_read_all_group_messages")) 
            can_read_all_group_messages = objJsonFrom.getBoolean("can_read_all_group_messages");

        Boolean supports_inline_queries = null;
        if(objJsonFrom.has("can_read_all_group_messages")) 
            supports_inline_queries = objJsonFrom.getBoolean("supports_inline_queries");

        String last_name = "NULL";
        if(objJsonFrom.has("last_name")) 
            last_name = objJsonFrom.getString("last_name");

        String username = "NULL";
        if(objJsonFrom.has("username")) 
            username = objJsonFrom.getString("username");

        String language_code = "NULL";
        if(objJsonFrom.has("language_code"))  
            language_code = objJsonFrom.getString("language_code");
        
         User from = new User(id, is_bot, can_join_groups, can_read_all_group_messages, supports_inline_queries, first_name, last_name, username, language_code);
        //
        JSONObject objChat = (JSONObject) res.get("chat"); //prendo l'object JSON chat da result
        String idChat = objChat.get("id").toString();
        
        String first_nameChat = "NULL";
        if(objChat.has("first_name"))
            first_nameChat = objChat.get("first_name").toString();
        
        String type = objChat.get("type").toString();
        
        Integer date = res.getInt("date");
        String text = res.get("text").toString();
        
        Chat chat = new Chat(id, type, first_name);
        
        mess = new Messaggio(message_id, from, date, chat, text);
    return mess;
    }
        private String readUrl(URL url) throws IOException
    {
        String inline = "";
        Scanner scanner = new Scanner(url.openStream());
  
        //Write all the JSON data into a string using a scanner
        while (scanner.hasNext()) {
           inline += scanner.nextLine();
        }
        //Close the scanner
        scanner.close();
        return inline;
    }
}
