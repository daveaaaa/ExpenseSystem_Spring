package persistanceLayer.mongoDB;

import java.net.UnknownHostException;
import persistanceLayer.DBHandler;
import persistanceLayer.DBHelper;
import persistanceLayer.NullDBHandler;

/**
 *
 * @author david
 */
public class MongoDBHelper extends DBHelper {

    public static DBHandler getDBHandler() throws Exception{

        MongoDBHandler handler = new MongoDBHandler(dbHost, dbName, dbUsername, dbPassword);
        return handler;

    }
}
