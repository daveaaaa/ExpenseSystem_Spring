package model;

import java.io.File;
import model.Image;

/**
 *
 * @author david
 */
public class Receipt {

    private String name;
    private Image image;
    private User user;
    
    public Receipt(){
        name = "";
        image = null; 
    }
    
    public Receipt(User user){
        this.user = user; 
        name = user.getUsername() + new java.util.Date().getTime();
    }
    
    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public User getUser(){
        return user;
    }
    
    public void setUser(User user){
        this.user = user; 
    }
}
