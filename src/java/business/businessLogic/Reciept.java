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

    public static business.businessModel.Receipt createReciept(MultipartFile multiPartFile, business.businessModel.User user) throws Exception {

        business.businessModel.Receipt receipt = new Receipt(user.getUserID());
        business.businessModel.ReceiptImage image = createImage(multiPartFile);
        receipt.setImage(image);

        //add receipt to db
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

        try {

            Document document = business.businessLogic.parseImage.AORC.ParseImageAORC.getInstance().parseImage(receipt);

            receipt = ParseXML.parseXML(document, receipt);

            //Push items to db
            DBHandler handler = MongoDBHelper.getDBHandler();
            handler.updateReceipt(receipt);

        } catch (Exception ex) {
            // TODO logging
        }
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

    public static boolean parseReceiptUpdateJSON(String json) {
        String receiptJSON = json.split(",\"item\":")[0];
        String itemJSON = json.split(",\"item\":")[1];
        
        business.businessModel.Receipt receipt = parseReceipt(receiptJSON);
        Item item = parseRecieptItem(itemJSON);
        
        makeAdujstments(receipt, item);
        
        return true;
    }

    private static void  makeAdujstments(business.businessModel.Receipt receipt, Item item){
        
    }
    
    
    private static business.businessModel.Receipt parseReceipt(String recieptJSON) {
        business.businessModel.Receipt receipt = null;
        
        recieptJSON = recieptJSON.replace("receipt", "");
        recieptJSON = recieptJSON.replace("\"", "");
        recieptJSON = recieptJSON.replace(":", "");
        recieptJSON = recieptJSON.replace("{", "");
        
        try{
        DBHandler handler = MongoDBHelper.getDBHandler();
        receipt = handler.getReceipt(recieptJSON);
        } catch (Exception ex) {
            // TODO logging
        }
        return receipt;
    }

    private static Item parseRecieptItem(String itemJSON) {
        itemJSON = itemJSON.replaceAll("\\{", "");
        itemJSON = itemJSON.replaceAll("\\}", "");
        String[] itemParts = itemJSON.split(",");

        Item item = new Item();

        for (String mapString : itemParts) {
            String keyStr = mapString.split(":")[0];
            String valueStr = mapString.split(":")[1];
            keyStr = keyStr.replaceAll("\"", "");
            valueStr = valueStr.replace("\"", "");

            if (valueStr.length() > 0) {
                try {
                    Object[] value = new Object[1];
                    Field field = Item.class.getDeclaredField(keyStr);
                    keyStr = keyStr.substring(0, 1).toUpperCase() + keyStr.substring(1);
                    if (field.getType() == Integer.TYPE) {
                        value[0] = Integer.parseInt(valueStr);
                    } else if (field.getType() == Double.TYPE) {
                        value[0] = Integer.parseInt(valueStr);
                    } else if (field.getType() == Boolean.TYPE) {
                        value[0] = Boolean.parseBoolean(valueStr);
                    } else if (field.getType() == ItemType.class) {
                        int valueInt = Integer.parseInt(valueStr);
                        for (ItemType type : ItemType.values()) {
                            if (type.getValue() == valueInt) {
                                value[0] = type;
                                break;
                            }
                        }
                    } else {
                        value[0] = valueStr;
                    }
                    java.beans.Statement stmt = new java.beans.Statement(item, "set" + keyStr, value);
                    stmt.execute();
                } catch (NoSuchFieldException nsf) {
                    //TODO more logging
                } catch (IllegalAccessException iae) {
                    //TODO more logging
                } catch (Exception ex) {
                    //TODO more logging
                }
            }
        }
        return item;
    }
}
