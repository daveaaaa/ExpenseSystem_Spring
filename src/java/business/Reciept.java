/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import model.Image;
import model.Receipt;
import org.springframework.web.multipart.MultipartFile;
import persistanceLayer.DBHandler;
import persistanceLayer.DBHelper;
import persistanceLayer.mongoDB.MongoDBHelper;

/**
 *
 * @author david
 */
public class Reciept {

    public static void createReciept(MultipartFile multiPartFile, model.User user) throws Exception {

        model.Receipt receipt = new Receipt(user);
        model.Image image = createImage(multiPartFile);
        receipt.setImage(image);

        DBHandler handler = MongoDBHelper.getDBHandler();

        handler.createReceipt(receipt);
    }

    private static model.Image createImage(MultipartFile multiPartFile) throws Exception {

        byte[] byteArray = multiPartFile.getBytes();
        BufferedImage image = ImageIO.read(multiPartFile.getInputStream());
        int height = image.getHeight();
        int width = image.getWidth();

        return new model.Image(byteArray, multiPartFile.getContentType(), height, width);

    }

}
