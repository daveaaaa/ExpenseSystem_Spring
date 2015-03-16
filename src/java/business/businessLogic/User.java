/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic;

import databaseAccess.DBHandler;
import databaseAccess.mongoDB.MongoDBHelper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class User {

    public static business.businessModel.User login(business.businessModel.User user) {
        business.businessModel.User foundUser = null;
        try {

            DBHandler handler = MongoDBHelper.getDBHandler();
            foundUser = handler.getUser(user.getUsername(), user.getPassword());

        } catch (Exception ex) {

        }
        return foundUser;
    }

    public static boolean addUser(business.businessModel.User user) {
        try {
            DBHandler handler = MongoDBHelper.getDBHandler();
            handler.createUser(user);

        } catch (Exception ex) {
            //TODO: logging
        }
        return true;
    }

    public static String getHomepage(business.businessModel.User user) {
        String view = "";
        try {

            switch (user.getSecurityGroup()) {
                case Admin:
                    view = "adminHomepage";
                    break;
                case DataAnalysis:
                    view = "";
                    break;
                case ReceiptProvider:
                    view = "receiptProviderHomepage";
                    break;
                case ReceiptManager:
                    view = "receiptManagerHomepage";
                    break;
                default:
                    view = "login";
            }

        } catch (Exception ex) {

        }
        return view;
    }

}
