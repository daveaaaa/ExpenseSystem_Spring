package persistanceLayer.mongoDB;

import java.net.UnknownHostException;
import persistanceLayer.DBHandler;
import persistanceLayer.Helper;
import persistanceLayer.NullDBHandler;

/**
 *
 * @author david
 */
public class MongoDBHelper extends Helper {

    public static DBHandler getDBHandler() {
        try {
            MongoDBHandler handler = new MongoDBHandler(dbHost, dbUser, dbName, dbPassword);
            return handler;
        } catch (UnknownHostException uhe) {
            System.exit(1);
        }
        
        return new NullDBHandler(); 
    }
}
