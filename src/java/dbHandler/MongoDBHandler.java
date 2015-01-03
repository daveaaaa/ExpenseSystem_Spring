/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbHandler;

import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import utils.Tuplet;

/**
 *
 * @author david
 */
public class MongoDBHandler {

    private MongoClient client;
    private DB db;

    public MongoDBHandler(String host, String dbName, String dbUser, String dbPassword) throws UnknownHostException {
        client = new MongoClient("localhost");
        db = client.getDB(dbName);

    }

    public void list(String collection) {
        DBCollection table = db.getCollection(collection);
        Set<String> tables = db.getCollectionNames();

        for (String coll : tables) {
            System.out.print(coll);
        }
    }
    
    public void save(ArrayList<Tuplet> information, String collection){
        DBCollection table = db.getCollection(collection);
        BasicDBObject document = new BasicDBObject();
        
        for(Tuplet attribute : information){
            document.put(attribute.getKey(), attribute.getAttribute());
        }
        
    }
}
