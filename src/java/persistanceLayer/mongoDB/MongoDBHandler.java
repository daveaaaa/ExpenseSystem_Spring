/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistanceLayer.mongoDB;

import com.mongodb.*;
import com.mongodb.gridfs.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import model.Receipt;
import model.User;
import persistanceLayer.DBHandler;
import utils.Tuplet;

/**
 *
 * @author david
 */
public class MongoDBHandler implements DBHandler {

    private String dbUsername;
    private String dbPassword;
    private MongoClient client;
    private DB db;

    private static final String USER_COLLECTION = "user";
    private static final String RECIEPT_COLLECTION = "reciept";
    private static final String IMAGE_COLLECTION = "image";
    private static final String IMAGE_METADATA_COLLECTION = "image_metadata"; 
    
    public MongoDBHandler(String dbHost, String dbUsername, String dbPassword) throws UnknownHostException {
        client = new MongoClient(dbHost);
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }
    
//    public void list(String collection) {
//        DBCollection table = db.getCollection(collection);
//        DBCursor cursor = table.find();
//
//        while (cursor.hasNext()) {
//            DBObject obj = cursor.next();
//            System.out.println(obj.toString());
//        }
//    }
//
//    public void save(ArrayList<Tuplet> information, String collection) {
//        DBCollection table = db.getCollection(collection);
//        BasicDBObject document = new BasicDBObject();
//
//        for (Tuplet attribute : information) {
//            document.put(attribute.getKey(), attribute.getAttribute());
//        }
//
//        table.insert(document);
//    }
//
//    public void saveImage(String filename, File image, String collection) throws IOException {
//        GridFS gfsPhoto = new GridFS(db, collection);
//        GridFSInputFile gfsFile = gfsPhoto.createFile(image);
//        gfsFile.setFilename(filename);
//        gfsFile.save();
//
//    }
//
//    public ArrayList getImage(String filename, String collection) {
//        GridFS gfsPhoto = new GridFS(db, collection);
//        GridFSDBFile imageForOutput = gfsPhoto.findOne(filename);
//        System.out.println(imageForOutput);
//
//        InputStream inputStream = imageForOutput.getInputStream();
//
//        ArrayList image = new ArrayList<>();
//        try {
//            int current = 0;
//            do {
//                current = inputStream.read();
//                image.add(current);
//            } while (current != -1);
//
//            inputStream.close();
//        } catch (IOException ioE) {
//            ioE.printStackTrace(System.out);
//            System.exit(1);
//        }
//
//        return image;
//    }

    @Override
    public void createReceipt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateReceipt(Receipt reciept) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Receipt> findReceipt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Receipt getReceipt(int userID, Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<User> findUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
