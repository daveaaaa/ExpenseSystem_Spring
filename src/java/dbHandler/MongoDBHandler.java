/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbHandler;

import com.mongodb.*;
import com.mongodb.gridfs.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    public MongoDBHandler(String host, String dbName) throws UnknownHostException {
        this(host, dbName, "", "");
    }

    public MongoDBHandler(String host, String dbName, String dbUser, String dbPassword) throws UnknownHostException {
        client = new MongoClient("localhost");
        db = client.getDB(dbName);

    }

    public void list(String collection) {
        DBCollection table = db.getCollection(collection);
        DBCursor cursor = table.find();

        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            System.out.println(obj.toString());
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

    public ArrayList getImage(String filename, String collection) {
        GridFS gfsPhoto = new GridFS(db, collection);
        GridFSDBFile imageForOutput = gfsPhoto.findOne(filename);
        System.out.println(imageForOutput);

        InputStream inputStream = imageForOutput.getInputStream();

        ArrayList image = new ArrayList<>();
        try {
            int current = 0;
            do {
                current = inputStream.read();
                image.add(current);
            } while (current != -1);

            inputStream.close();
        } catch (IOException ioE) {
            ioE.printStackTrace(System.out);
            System.exit(1);
        }
        
        
        return image; 
    }
}
