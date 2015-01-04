/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistanceLayer.mongoDB;

import persistanceLayer.DBHandler;
import persistanceLayer.Helper;

/**
 *
 * @author david
 */
public class MongoDBHelper extends Helper{
    
    
    @Override
    public static DBHandler getDBHandler(){
        MongoDBHandler handler = new MongoDBHandler(dbHost, dbUser, dbPassword);
        
        
    }
}
