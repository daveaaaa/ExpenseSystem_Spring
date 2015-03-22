/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic;

import business.businessLogic.parseImage.ParseXML;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import business.businessModel.Receipt;
import org.springframework.web.multipart.MultipartFile;
import databaseAccess.DBHandler;
import databaseAccess.mongoDB.MongoDBHelper;
import java.util.ArrayList;
import org.w3c.dom.Document;

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

    public static Receipt parseReceipt(Receipt receipt) {

        business.businessLogic.parseImage.ParseImage imageParser = new business.businessLogic.parseImage.AORC.ParseImageAORC();

        Document document = imageParser.parseImage(receipt);

        receipt = ParseXML.parseXML(document, receipt);

        return receipt;
    }

    public static ArrayList<business.businessModel.Receipt> listReceipts(business.businessModel.User user) {
        ArrayList<business.businessModel.Receipt> receiptList = new ArrayList<>();
        try {
            DBHandler handler = MongoDBHelper.getDBHandler();

            if (user.getSecurityGroup() != business.businessModel.SecurityGroup.ReceiptProvider) {
                receiptList = handler.listAllReceipts();
            } else {
                receiptList = handler.listReceipts(user.getUserID());
            }
        } catch (Exception ex) {
            //TODO: log exceptions
        }
        return receiptList;
    }

}
