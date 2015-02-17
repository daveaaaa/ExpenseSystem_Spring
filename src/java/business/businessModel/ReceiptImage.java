/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessModel;

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
public class ReceiptImage {

    private int height;
    private int width;
    private String format;
    private String base64;
    private byte[] byteArray;
    private String fileFormat;
    
    public ReceiptImage() {
        base64 = "";
        byteArray = new byte[0];
        width = 0;
        height = 0;
        format = "";
        fileFormat = "";
    }

    public ReceiptImage(byte[] byteArray, String format, int height, int width) throws IOException {
        this.byteArray = byteArray;
        this.base64 = Base64.encode(byteArray);
        this.format = format;
        this.height = height;
        this.width = width;
        parseFileFormat();
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

    public String getFileFormat() {
        return fileFormat;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
        this.base64 = Base64.encode(byteArray);
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setBase64(String string) throws Base64DecodingException {
        base64 = string;
        byteArray = Base64.decode(string);
    }

    public String getBase64() {
        if (base64.isEmpty() & byteArray.length > 0) {
            base64 = Base64.encode(byteArray);
        }
        return base64;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setFormat(String format) {
        this.format = format;
        parseFileFormat();
    }

    private void parseFileFormat() {
        if (!format.equals("")) {
            fileFormat = format.replace("image/", "");
        }
    }
    
    
}
