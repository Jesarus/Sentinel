/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.actors;

import java.util.ArrayList;
import java.util.List;
import server.actors.actions.Post;

/**
 *
 * @author David
 *         Gabriel Mendes
 *         Gabriel David
 */
public class User extends AbstractActor {

    private String password;
    private int gender;
    private final String dob;
    
    //3rd person wall post
    private List<Post> others_posts;
    private boolean isPublicWall;
    
    public User(String name, String email, String password, int gender, String dob){
        super(name, email);
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        
        isPublicWall = true;
        
        others_posts = new ArrayList<>();
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String newPassword){
        password = newPassword;
    }
    
    public String getGender(){
        switch(gender){
            case 0:
                return "Masculino";
            case 1:
                return "Feminino";
            default:
                return "Outro";
        }
    }
    
    public void setGender(int newGender){
        gender = newGender;
    }
    
    public String getDob(){
        return dob;
    }
    
    public void addPostFromOthers(Post newPost){
        others_posts.add(newPost);
    }
    
    public void removePostFromOthers(int index){
        others_posts.remove(index);
    }
    
    public List<Post> getPostsFromOthers(){
        return others_posts;
    }
    
    public Post getPostFromOthersByIndex(int index){
        return others_posts.get(index);
    }
    
    public boolean isPublicWall(){
        return isPublicWall;
    }
    
    public void setWallVisibility(boolean newVisibility){
        isPublicWall = newVisibility;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
