/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library2;

/**
 *
 * @author gabri
 */
public class User {
      String id;
    Boolean is_bot, can_join_groups, can_read_all_group_messages, supports_inline_queries;
    String first_name, last_name, username, language_code;
     public User(){ //costruttore vuoto e silenzio
     
     }
      public User(String id, Boolean is_bot, Boolean can_join_groups, Boolean can_read_all_group_messages, Boolean supports_inline_queries, String first_name, String username) {
        this.id = id;
        this.is_bot = is_bot;
        this.can_join_groups = can_join_groups;
        this.can_read_all_group_messages = can_read_all_group_messages;
        this.supports_inline_queries = supports_inline_queries;
        this.first_name = first_name;
        this.username = username;
    }
        public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIs_bot() {
        return is_bot;
    }

    public void setIs_bot(Boolean is_bot) {
        this.is_bot = is_bot;
    }

    public Boolean getCan_join_groups() {
        return can_join_groups;
    }

    public void setCan_join_groups(Boolean can_join_groups) {
        this.can_join_groups = can_join_groups;
    }

    public Boolean getCan_read_all_group_messages() {
        return can_read_all_group_messages;
    }

    public void setCan_read_all_group_messages(Boolean can_read_all_group_messages) {
        this.can_read_all_group_messages = can_read_all_group_messages;
    }

    public Boolean getSupports_inline_queries() {
        return supports_inline_queries;
    }

    public void setSupports_inline_queries(Boolean supports_inline_queries) {
        this.supports_inline_queries = supports_inline_queries;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

      
}
