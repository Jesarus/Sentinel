/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.actors.actions;

import java.io.Serializable;
import server.actors.User;

/**
 *
 * @author David
 *         Gabriel Mendes
 *         Gabriel David
 */
public class Answer implements Serializable {
    
    private String text;
    private User author;
    
    public Answer(String text, User author){
        this.text = text;
        this.author = author;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String newText){
        text = newText;
    }
    
    public User getAuthor(){
        return author;
    }
    
    public void setAuthor(User newAuthor){
        author = newAuthor;
    }
}
