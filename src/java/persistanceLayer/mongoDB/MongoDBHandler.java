package persistanceLayer.mongoDB;

import com.mongodb.*;
import com.mongodb.gridfs.*;
import model.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import model.Receipt;
import model.User;
import org.apache.commons.io.FilenameUtils;
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

    private static final String FILENAME = "filename";
    
    
    public MongoDBHandler(String dbHost, String dbName, String dbUsername, String dbPassword) throws UnknownHostException {
        client = new MongoClient(dbHost);
        db = client.getDB(dbName);
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    private DBCollection getReceiptCollection() {
        return db.getCollection(RECIEPT_COLLECTION);
    }

    private GridFS getImageCollection() {
        return new GridFS(db, IMAGE_COLLECTION);
    }

    private DBCollection getUserCollection() {
        return db.getCollection(USER_COLLECTION);
    }

    private BasicDBObject getDocumentFromReceipt(Receipt receipt) {
        return new BasicDBObject();
    }

    //Create Receipt
    @Override
    public void createReceipt(Receipt reciept) {
        saveReceipt(reciept);
        saveImage(reciept);
    }

    private void saveReceipt(Receipt reciept) {
        DBCollection table = getReceiptCollection();
        BasicDBObject document = getDocumentFromReceipt(reciept);
        
        document.put(FILENAME, reciept.getName());
        document.put("Image_MetaData", saveImageMetaData(reciept));
        
        table.insert(document);
    }

    private void saveImage(Image image, String filename) {
        try {
            GridFS gfsPhoto = getImageCollection();
            GridFSInputFile gfsFile = gfsPhoto.createFile(image.getImageFile());
            gfsFile.setFilename(filename);
            gfsFile.save();
        } catch (IOException ioE) {
            //TODO: implement logging
            System.exit(1);
            //do nothing
        }
    }

    private BasicDBObject saveImageMetaData(Receipt receipt) {

        BasicDBObject metaData = new BasicDBObject();

        metaData.put("format", receipt.getImage().getFormat());
        metaData.put("height", receipt.getImage().getHeight());
        metaData.put("width", receipt.getImage().getWidth());

        return metaData;

    }

    private void saveImage(Receipt receipt) {
        saveImage(receipt.getImage(), receipt.getName());
    }

    //Update Receipt
    @Override
    public void updateReceipt(Receipt reciept) {
        DBCollection table = getReceiptCollection();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Receipt> findReceipt() {
        DBCollection table = getReceiptCollection();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Receipt getReceipt(int userID, Date startDate, Date endDate) {
        DBCollection table = getReceiptCollection();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createUser(User user) {
        DBCollection table = getUserCollection();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUser(User user) {
        DBCollection table = getUserCollection();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<User> findUser() {
        DBCollection table = getUserCollection();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(String username, String password) {
        DBCollection table = getUserCollection();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
