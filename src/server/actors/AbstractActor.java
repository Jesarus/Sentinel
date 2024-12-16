/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.actors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import server.actors.actions.Photo;
import server.actors.actions.Post;

/**
 *
 * @author David
 *         Gabriel Mendes
 *         Gabriel David
 */
public abstract class AbstractActor implements Serializable{
    private static final long serialVersionUID = 19618L;

    protected String name;
    protected String id;
    protected ImageIcon icon;

    protected List<User> related;
    protected List<User> request; 
    protected List<User> blocked;
    
    protected List<Post> posts;
    protected List<Photo> photos;
    
    /**
     * Creates a new abstract actor
     * @param name The Actor's name
     * @param id The Actor's id, used for the mapping.
     */
    public AbstractActor(String name, String id){
        this.name = name;
        this.id = id;
        
        // corrigir
        if(this instanceof User){
            this.icon = new ImageIcon(getClass().getResource("/assets/default_profile.png"));
        } else {
            this.icon = new ImageIcon(getClass().getResource("/assets/default_group.png"));
        }
        
        related = new ArrayList<>();
        request = new ArrayList<>();
        blocked = new ArrayList<>();
        
        posts = new ArrayList<>();
        photos = new ArrayList<>();
    }
    
    /**
     * Returns this actor name.
     * @return The Actor's name.
     */
    public String getName(){
        return name;
    }
    
    /**
     * Gives a new name to this actor.
     * @param newName The actor's new name.
     */
    public void setName(String newName){
        name = newName;
    }
    
    /**
     * Gives the ID of this actor.
     * @return The Mail if the actor is a User or the group's username if the actor is a Group.
     */
    public String getId(){
        return id;
    }
    
    /**
     * Gives a new ID to this actor.
     * @param newId The Actor's new ID
     * @deprecated Unsafe, may crash the database.
     */
    @Deprecated
    public void setId(String newId){
        id = newId;
    }
    
    /**
     * Gets the Icon of this Actor.
     * @return The ImageIcon of this Actor.
     */
    public ImageIcon getIcon(){
        return icon;
    }
    
    /**
     * Sets a new Icon for this Actor.
     * @param newIcon The new Icon for this actor.
     */
    public void setIcon(ImageIcon newIcon){
        icon = newIcon;
    }
    
    /**
     * Adds a new relative this actor (friend/member).
     * @param newRelative The user to be added.
     */
    public void addRelative(User newRelative){
        if(!isRelative(newRelative)){
            related.add(newRelative);
            Object ob = this;
            if(ob instanceof User){
                newRelative.addRelative((User) ob);
            }
        }
        removeRequest(newRelative);
    }
    
    /**
     * Ends a relationship beetwen this actor and the param.
     * @param relativeToRemove The user to be removed.
     */
    public void removeRelative(User relativeToRemove){
        Iterator<User> it = related.iterator();
        while(it.hasNext()){
            if(relativeToRemove.getId().equals(it.next().getId())){
                it.remove();
                Object ob = this;
                if(ob instanceof User){
                    relativeToRemove.removeRelative((User) ob);
                } else if(ob instanceof Group){
                    Group group = (Group) ob;
                    group.removeAdmin(relativeToRemove);
                }
                break;
            }
        }
    }
    
    /**
     * Tests if a user has a relationship with this actor.
     * @param relative The user to test.
     * @return If the user has a relationship with this actor.
     */
    public boolean isRelative(User relative){
        Iterator<User> it = related.iterator();
        while(it.hasNext()){
            if(relative.getId().equals(it.next().getId())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Shows the list of relatives of this actor.
     * @return An ArrayList with the related users.
     */
    public List<User> getRelatives(){
        return related;
    }
    
    /**
     * Sends a new Request to this actor.
     * @param requester The user that is requesting.
     */
    public void sendNewRequest(User requester){
        if(!isRequestSent(requester) && !isBlocked(requester)){
            request.add(requester);
        }
    }
    
    /**
     * Shows this actor requests.
     * @return An ArrayList with the users requesting.
     */
    public List<User> getRequests(){
        return request;
    }
    
    /**
     * Removes a request from the request list.
     * @param toRemove The User to be removed from the list.
     */
    public void removeRequest(User toRemove){
        Iterator<User> it = request.iterator();
        while(it.hasNext()){
            if(toRemove.getId().equals(it.next().getId())){
                it.remove();
                break;
            }
        }
    }
    
    /**
     * Avoids a duplicate request.
     * @param requester The user that is requesting.
     * @return If a request has been previously sent.
     */
    public boolean isRequestSent(User requester){
        Iterator<User> it = request.iterator();
        while(it.hasNext()){
            if(requester.getId().equals(it.next().getId())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Shows the list of Users blocked by this actor.
     * @return An ArrayList with the users blocked by this actor.
     */
    public List<User> getBlocked(){
        return blocked;
    }
    
    /**
     * Checks if a user has been blocked by this actor.
     * @param toSearch The user to search.
     * @return If a user has been blocked by this actor.
     */
    public boolean isBlocked(User toSearch){
        Iterator<User> it = blocked.iterator();
        while(it.hasNext()){
            if(toSearch.getId().equals(it.next().getId())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Blocks a user
     * @param victim The user to block 
     */
    public void block(User victim){
        if(!isBlocked(victim)){
            getBlocked().add(victim);
            removeRelative(victim);
            Object ob = this;
            if(ob instanceof User){
                victim.removeRelative((User) ob);
                victim.removeRequest((User) ob);
            }
            removeRequest(victim);
        }
    }
    
    /**
     * Unblocks a user
     * @param toUnblock The user to unblock
     */
    public void unblock(User toUnblock){
        Iterator<User> it = blocked.iterator();
        while(it.hasNext()){
            if(toUnblock.getId().equals(it.next().getId())){
                it.remove();
                break;
            }
        }
    }
    
    /**
     * Adds a new post to this actor's wall.
     * @param newPost The post to be added.
     */
    public void addPost(Post newPost){
        posts.add(newPost);
    }
    
    /**
     * Removes a post from this actor's wall by index.
     * @param index The index of the post to be removed.
     */
    public void removePost(int index){
        posts.remove(index);
    }
    
    /**
     * Shows the posts on this actor's wall.
     * @return An ArrayList with this actor's posts.
     */
    public List<Post> getPosts(){
        return posts;
    }
    
    /**
     * Gets a post from this actor's wall by index.
     * @param index The index to be searched.
     * @return A post from this index.
     */
    public Post getPost(int index){
        return posts.get(index);
    }
    
    /**
     * Gives the list of photos of this actor.
     * @return An ArrayList with the photos from this actor.
     */
    public List<Photo> getPhotos(){
        return photos;
    }
    
    /**
     * Gets a photo from this actor on a certain index.
     * @param index The index to be searched.
     * @return The photo on the index.
     */
    public Photo getPhoto(int index){
        return photos.get(index);
    }
        
    /**
     * Deletes a photo from this actor on a certain index.
     * @param index The index to be searched.
     */
    public void deletePhoto(int index){
        photos.remove(index);
    }
    
    /**
     * Adds a photo to this actor.
     * @param newPhoto The photo to be added.
     */
    public void addPhoto(Photo newPhoto){
        photos.add(newPhoto);
    }
}