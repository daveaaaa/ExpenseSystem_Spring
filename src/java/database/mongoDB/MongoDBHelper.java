package database.mongoDB;

import java.net.UnknownHostException;
import database.DBHandler;
import database.DBHelper;
import database.NullDBHandler;

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
