package databaseAccess.mongoDB;

import java.net.UnknownHostException;
import databaseAccess.DBHandler;
import databaseAccess.DBHelper;
import databaseAccess.NullDBHandler;

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
