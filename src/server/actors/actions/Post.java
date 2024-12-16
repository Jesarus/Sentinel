/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.actors.actions;

import server.actors.User;
import java.io.Serializable;

/**
 *
 * @author David
 *         Gabriel Mendes
 *         Gabriel David
 */
public class Post extends Comment implements Serializable {
    
    private String title;
    private boolean isPublic;

    public Post(String title, String text, User author, boolean isPublic){
        super(text, author);
        this.title = title;
        this.isPublic = isPublic;              
    }
    
    public Post(String title, String text, User author, String iconPath, boolean isPublic){
        super(text, author, iconPath);
        this.title = title;
        this.isPublic = isPublic;
    }
        
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String newTitle){
        this.title = newTitle;
    }
    
    public boolean isPublic(){
        return isPublic;
    }
    
    public void setVisible(boolean toPublic){
        isPublic = toPublic;
    }
}
