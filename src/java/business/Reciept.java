/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.File;
import model.Image;
import model.Receipt;
import persistanceLayer.DBHandler;
import persistanceLayer.DBHelper;
import persistanceLayer.mongoDB.MongoDBHelper;

/**
 *
 * @author david
 */
public class Reciept {

    public static void createReciept(File image, model.User user) throws Exception {

        model.Receipt receipt = new Receipt(user);
        receipt.setImage(new Image(image));

        DBHandler handler = MongoDBHelper.getDBHandler();
        
        handler.createReceipt(receipt);
    }

}
