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

    public Receipt(){
        name = "";
        image = null; 
    }
    
    public Receipt(User user){
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
}
