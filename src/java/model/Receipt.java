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
