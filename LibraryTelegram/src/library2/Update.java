/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library2;

/**
 *
 * @author gabri
 */
public class Update {
    Integer update_id;
    Messaggio message;

   

    public Update(Integer update_id, Messaggio message) {
        this.update_id = update_id;
        this.message = message;
    }
    
    public Integer getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(Integer update_id) {
        this.update_id = update_id;
    }

    public Messaggio getMessage() {
        return message;
    }

    public void setMessage(Messaggio message) {
        this.message = message;
    }

     @Override
    public String toString() {
        return "Update{" + "update_id=" + update_id + ", message=" + message + '}';
    }
}
