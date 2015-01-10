/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
    private byte[] byteArray;

    public Image() {
        byteArray = new byte[0];
        width = 0;
        height = 0;
        format = "";
    }

    public Image(byte[] byteArray, String format, int height, int width) throws IOException {
        this.byteArray = byteArray;
        this.format = format;
        this.height = height;
        this.width = width;

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

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(String string) throws Base64DecodingException {
        byteArray = Base64.decode(string);
    }

    public String get64EnCode() {
        return Base64.encode(byteArray);
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
}
