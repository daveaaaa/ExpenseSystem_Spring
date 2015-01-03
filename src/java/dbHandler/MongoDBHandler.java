/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbHandler;

import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.File;
import java.io.IOException;
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

    public void save(ArrayList<Tuplet> information, String collection) {
        DBCollection table = db.getCollection(collection);
        BasicDBObject document = new BasicDBObject();

        for (Tuplet attribute : information) {
            document.put(attribute.getKey(), attribute.getAttribute());
        }
        
        table.insert(document);
    }

    public void saveImage(String filename, File image, String collection) throws IOException {
        GridFS gfsPhoto = new GridFS(db, collection);
        GridFSInputFile gfsFile = gfsPhoto.createFile(image);
        gfsFile.setFilename(filename);
        gfsFile.save();

    }

    public void getImage(String filename, String collection) {
        GridFS gfsPhoto = new GridFS(db, collection);
        GridFSDBFile imageForOutput = gfsPhoto.findOne(filename);
        System.out.println(imageForOutput);
    }
}
