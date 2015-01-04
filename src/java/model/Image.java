/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author david
 */
public class Image extends File {

    private int height;
    private int width;
    private String format;
   
    
    public Image(String pathname) throws IOException{
        super(pathname);
        
        BufferedImage image = ImageIO.read(this);

        format = FilenameUtils.getExtension(this.getPath());
        height = image.getHeight();
        width = image.getWidth();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getFormat() {
        return format;
    }

    
    
}
