/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic;

import databaseAccess.DBHandler;
import databaseAccess.mongoDB.MongoDBHelper;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class User {

    public static business.businessModel.User findUser(String userID, DBHandler handler) {
        business.businessModel.User user = new business.businessModel.User();
        try {
            user = handler.findUser(userID);
        } catch (Exception ex) {
            //TODO: log exception
        }
        return user;
    }

    public static ArrayList<business.businessModel.User> getUsers(DBHandler handler) {
        ArrayList<business.businessModel.User> userList = new ArrayList<>();
        try {

            userList = handler.findUser();

        } catch (Exception ex) {
            //TODO: log exception 
        }
        return userList;
    }

    public static business.businessModel.User login(business.businessModel.User user, DBHandler handler) {
        business.businessModel.User foundUser = null;
        try {

            foundUser = handler.getUser(user.getUsername(), user.getPassword());

        } catch (Exception ex) {
            //TODO: log exception
        }
        return foundUser;
    }

    public static boolean addUser(business.businessModel.User user, DBHandler handler) {
        try {
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

    public static void deleteUser(String userID, DBHandler handler) {
        try {

            handler.deleteUser(userID);

        } catch (Exception ex) {
            //TODO: Logging
        }
    }

    public static void updateUser(business.businessModel.User user, DBHandler handler) {
        try {
            handler.updateUser(user);
        } catch (Exception ex) {
            //TODO: Logging
        }
    }

}
