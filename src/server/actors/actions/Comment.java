/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.actors.actions;

import server.actors.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author David
 *         Gabriel Mendes
 *         Gabriel David
 */
public class Comment extends Answer implements Serializable {
    
    private final ImageIcon icon;
    
    private List<Comment> comments;
    private List<Answer> answers;
    
    public Comment(String text, User author){
        super(text, author);
        
        icon = null;
        comments = new ArrayList<>();
        answers = new ArrayList<>();
    }
    
    public Comment(String text, User author, String iconPath){
        super(text, author);
        
        icon = new ImageIcon(iconPath);     
        comments = new ArrayList<>();
        answers = new ArrayList<>();
    }
    
    public ImageIcon getIcon(){
        return icon;
    }
    
    public void addComment(Comment newComment){
        comments.add(newComment);
    }
    
    public List<Comment> getComments(){
        return comments;
    }
    
    public void addAnswer(Answer newAnswer){
        answers.add(newAnswer);
    }
    
    public List<Answer> getAnswers(){
        return answers;
    }
}
