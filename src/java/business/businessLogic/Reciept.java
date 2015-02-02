/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import business.businessModel.ReceiptImage;
import business.businessModel.Receipt;
import org.springframework.web.multipart.MultipartFile;
import databaseAccess.DBHandler;
import databaseAccess.DBHelper;
import databaseAccess.mongoDB.MongoDBHelper;

/**
 *
 * @author david
 */
public class Reciept {

    public static business.businessModel.Receipt createReciept(MultipartFile multiPartFile, business.businessModel.User user) throws Exception {

        business.businessModel.Receipt receipt = new Receipt(user.getUserID());
        business.businessModel.ReceiptImage image = createImage(multiPartFile);
        receipt.setImage(image);

        DBHandler handler = MongoDBHelper.getDBHandler();

        handler.createReceipt(receipt);
        
        receipt = handler.getReceipt(receipt.getReceiptID()); 
        return receipt;
    }

    private static business.businessModel.ReceiptImage createImage(MultipartFile multiPartFile) throws Exception {

        byte[] byteArray = multiPartFile.getBytes();
        BufferedImage image = ImageIO.read(multiPartFile.getInputStream());
        int height = image.getHeight();
        int width = image.getWidth();

        return new business.businessModel.ReceiptImage(byteArray, multiPartFile.getContentType(), height, width);

    }
    
    public static void parseReceipt(){
        
    }

}
