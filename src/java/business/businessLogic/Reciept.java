/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic;

import business.businessLogic.parseImage.ParseXML;
import business.businessModel.Item;
import business.businessModel.ItemType;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import business.businessModel.Receipt;
import org.springframework.web.multipart.MultipartFile;
import databaseAccess.DBHandler;
import databaseAccess.DBHelper;
import databaseAccess.mongoDB.MongoDBHelper;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.w3c.dom.Document;

/**
 *
 * @author david
 */
public class Reciept {

    public static business.businessModel.Receipt createReciept(MultipartFile multiPartFile, business.businessModel.User user, DBHandler handler) throws Exception {

        business.businessModel.Receipt receipt = new Receipt(user.getUserID());
        business.businessModel.ReceiptImage image = createImage(multiPartFile);
        receipt.setImage(image);

        //add receipt to db 
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

    public static Receipt parseReceipt(Receipt receipt, DBHandler handler) {

        try {

            Document document = business.businessLogic.parseImage.AORC.ParseImageAORC.getInstance().parseImage(receipt);

            receipt = ParseXML.parseXML(document, receipt);

            //Push items to db 
            handler.updateReceipt(receipt);

        } catch (Exception ex) {
            // TODO logging
        }
        return receipt;
    }

    public static ArrayList<business.businessModel.Receipt> listReceipts(business.businessModel.User user, DBHandler handler) {
        ArrayList<business.businessModel.Receipt> receiptList = new ArrayList<>();
        try {

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

    public static boolean parseReceiptUpdateJSON(Receipt receipt, Item item, DBHandler handler) {

        makeAdujstments(receipt, item, handler);

        return true;
    }

    private static void makeAdujstments(business.businessModel.Receipt receipt, Item item, DBHandler handler) {
        if (item.getID() == -1) {
            receipt.getReceiptItems().addItem(item);
        } else {
            receipt.getReceiptItems().replaceItem(item);
        }

        try {
            handler.updateReceipt(receipt);
        } catch (Exception ex) {
            // TODO logging
        }

    }

    public static void finalizeReceipt(Receipt receipt, DBHandler dbHandler) {

        receipt.setFinalized(true);
        dbHandler.updateReceipt(receipt);
    }
}
