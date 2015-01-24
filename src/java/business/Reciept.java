/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import business.model.Image;
import business.model.Receipt;
import org.springframework.web.multipart.MultipartFile;
import database.DBHandler;
import database.DBHelper;
import database.mongoDB.MongoDBHelper;

/**
 *
 * @author david
 */
public class Reciept {

    public static business.model.Receipt createReciept(MultipartFile multiPartFile, business.model.User user) throws Exception {

        business.model.Receipt receipt = new Receipt(user.getUserID());
        business.model.Image image = createImage(multiPartFile);
        receipt.setImage(image);

        DBHandler handler = MongoDBHelper.getDBHandler();

        handler.createReceipt(receipt);
        
        receipt = handler.getReceipt(receipt.getReceiptID()); 
        return receipt;
    }

    private static business.model.Image createImage(MultipartFile multiPartFile) throws Exception {

        byte[] byteArray = multiPartFile.getBytes();
        BufferedImage image = ImageIO.read(multiPartFile.getInputStream());
        int height = image.getHeight();
        int width = image.getWidth();

        return new business.model.Image(byteArray, multiPartFile.getContentType(), height, width);

    }

}
