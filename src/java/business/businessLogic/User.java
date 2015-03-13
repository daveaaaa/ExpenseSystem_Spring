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
    
    public static boolean login(business.businessModel.User user){
        return true; 
    }
    
    public static boolean addUser(business.businessModel.User user){
        try {
            DBHandler handler = MongoDBHelper.getDBHandler();
            handler.createUser(user);
        
        } catch (Exception ex) {
          //TODO: logging
        }
        return true; 
    }
    
}
