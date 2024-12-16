/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.actors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author David
 *         Gabriel Mendes
 *         Gabriel David
 */
public class Group extends AbstractActor {
    
    private List<User> admins;
    
    public Group(String name, String id, User founder) {
        super(name, id);
        
        related.add(founder);
        
        admins = new ArrayList<>();
        admins.add(founder);
    }
    
    public List<User> getAdmins(){
        return admins;
    }
    
    public void addAdmin(User newAdmin){
        if(!isAdmin(newAdmin)){
            admins.add(newAdmin);
        }
    }
    
    public void removeAdmin(User adminToRemove){
        Iterator<User> it = admins.iterator();
        while(it.hasNext()){
            if(adminToRemove.getId().equals(it.next().getId())){
                it.remove();
                break;
            }
        }
    }
    
    public boolean isAdmin(User admin){
        Iterator<User> it = admins.iterator();
        while(it.hasNext()){
            if(admin.getId().equals(it.next().getId())){
                return true;
            }
        }
        return false;
    }
}
