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
public class Image {

    private int height;
    private int width;
    private String format;
    private File imageFile;

    public Image(File file) throws IOException {
        format = FilenameUtils.getExtension(file.getPath());
        this.imageFile = file;
        setImageMetaData();
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

    public File getImageFile() {
        return imageFile;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setImageFile(File imageFile) throws IOException {
        this.imageFile = imageFile;
        setImageMetaData();
    }

    private void setImageMetaData() throws IOException {
        BufferedImage image = ImageIO.read(imageFile);
        height = image.getHeight();
        width = image.getWidth();
    }
}
