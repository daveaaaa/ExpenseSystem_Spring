package databaseAccess.mongoDB;
 
import databaseAccess.DBHandler;
import databaseAccess.DBHelper; 

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
