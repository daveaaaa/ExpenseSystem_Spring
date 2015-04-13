/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controller;

import business.businessModel.Item;
import business.businessModel.ItemType;
import business.businessModel.Receipt;
import databaseAccess.DBHandler;
import databaseAccess.mongoDB.MongoDBHelper;
import java.lang.reflect.Field;

/**
 *
 * @author david
 */
public class JSONParser {

    public static business.businessModel.Receipt getReceipt(String json, DBHandler handler) {
        String receiptJSON = json.split(",\"item\":")[0];
        String itemJSON = json.split(",\"item\":")[1];

        return parseReceipt(receiptJSON, handler);
    }

    public static business.businessModel.Item getItem(String json) {
        String receiptJSON = json.split(",\"item\":")[0];
        String itemJSON = json.split(",\"item\":")[1];

        return parseRecieptItem(itemJSON);
    }

    public static business.businessModel.Receipt finalizeReceipt(String json, DBHandler handler) {

        json = json.replace("\"", "");
        json = json.replace("}", "");
        json = json.replace("{", "");
        json = json.replace(":", "");
        json = json.replace("receiptID", "");

        String receiptID = json;

        return handler.getReceipt(receiptID);

    }

    private static business.businessModel.Receipt parseReceipt(String recieptJSON, DBHandler handler) {
        business.businessModel.Receipt receipt = null;

        recieptJSON = recieptJSON.replace("receipt", "");
        recieptJSON = recieptJSON.replace("\"", "");
        recieptJSON = recieptJSON.replace(":", "");
        recieptJSON = recieptJSON.replace("{", "");

        try {
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
                        value[0] = Double.parseDouble(valueStr);
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
