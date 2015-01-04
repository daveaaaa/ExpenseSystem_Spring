/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistanceLayer.mongoDB;

import java.net.UnknownHostException;
import persistanceLayer.DBHandler;
import persistanceLayer.Helper;
import persistanceLayer.NullHandler;

/**
 *
 * @author david
 */
public class MongoDBHelper extends Helper {

    public static DBHandler getDBHandler() {
        try {
            MongoDBHandler handler = new MongoDBHandler(dbHost, dbUser, dbPassword);
            return handler;
        } catch (UnknownHostException uhe) {
            System.exit(1);
        }
        
        return new NullHandler(); 
    }
}
