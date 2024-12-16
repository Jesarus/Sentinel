/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import server.actors.Group;
import server.actors.User;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David
 *         Gabriel Mendes
 *         Gabriel David
 */
public class Database implements Serializable{
    private static final long serialVersionUID = 147788L;

    private Map<String, User> users;
    private Map<String, Group> groups;
    
    private Database(){
        users = new HashMap<>();
        groups = new HashMap<>();
    }
    
    public static Database load(){
        try (FileInputStream fileIn = new FileInputStream("database.ser"); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (Database) in.readObject();
        } catch (IOException | ClassNotFoundException i) {
            return new Database();
        }     
    }
    
    public void save(){
        try (FileOutputStream fileOut = new FileOutputStream("database.ser"); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
        } catch (IOException i) {
        }
    }
    
    public void addUserToDb(User newUser){
        users.put(newUser.getId(), newUser);
    }
    
    public Map<String, User> getUsers(){
        return users;
    }
    
    public boolean emailInUse(String email){
        return users.containsKey(email);
    }
    
    public void addGroupToDb(Group newGroup){
        groups.put(newGroup.getId(), newGroup);
    }
    
    public void removeGroupFromDb(Group toRemove){
        groups.remove(toRemove.getId());
    }
    
    public Map<String, Group> getGroups(){
        return groups;
    }
        
    public boolean groupIdInUse(String group){
        return groups.containsKey(group);
    }
    
    public User getFromMail(String email){
        if(users.containsKey(email)){
            return users.get(email);
        } else {
            return null;
        }
    }
    
    public Group getFromGroupId(String group){
        if(groups.containsKey(group)){
            return groups.get(group);
        } else {
            return null;
        }
    }
    
    public String getPassword(String email){
        return users.get(email).getPassword();
    }
}
