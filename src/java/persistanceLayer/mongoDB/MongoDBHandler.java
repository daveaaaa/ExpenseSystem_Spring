package persistanceLayer.mongoDB;

import com.mongodb.*;
import com.mongodb.gridfs.*;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
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
import org.bson.types.ObjectId;
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

    public MongoDBHandler(String dbHost, String dbName, String dbUsername, String dbPassword) throws UnknownHostException {
        client = new MongoClient(dbHost);
        db = client.getDB(dbName);
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    private DBCollection getReceiptCollection() {
        return db.getCollection(RECIEPT_COLLECTION);
    }

    private DBCollection getUserCollection() {
        return db.getCollection(USER_COLLECTION);
    }

    private BasicDBObject getDocumentFromReceipt(Receipt receipt) {
        return new BasicDBObject();
    }

    //Create Receipt
    @Override
    public Receipt createReceipt(Receipt reciept) {
        reciept = saveReceipt(reciept);
        return reciept; 
    }

    private Receipt saveReceipt(Receipt reciept) {
        DBCollection table = getReceiptCollection();
        BasicDBObject document = getDocumentFromReceipt(reciept);

        document.put("userID", reciept.getUserID());
        document.put("createdDate", new java.util.Date().getTime());
        document.put("image", saveImage(reciept));

        table.insert(document);
        
        reciept.setReceiptID((String)document.get( "_id" ));
        
        return reciept; 
    }

    private BasicDBObject saveImage(Receipt receipt) {

        BasicDBObject metaData = new BasicDBObject();

        metaData.put("64bit", receipt.getImage().get64EnCode());
        metaData.put("format", receipt.getImage().getFormat());
        metaData.put("height", receipt.getImage().getHeight());
        metaData.put("width", receipt.getImage().getWidth());

        return metaData;

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
    public Receipt getReceipt(String userID, Date startDate, Date endDate) {
        DBCollection table = getReceiptCollection();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public model.Receipt getReceipt(String receiptID) {
        DBCollection table = getReceiptCollection();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(receiptID));
        DBObject dbObj = table.findOne(query);

        return createReceipt(dbObj);
    }

    private model.Receipt createReceipt(DBObject dbObj) {
        Receipt receipt = new Receipt();

        receipt.setReceiptID((String) dbObj.get("_id"));
        receipt.setUserID((String) dbObj.get("userID"));
        receipt.setCreatedDate(new java.util.Date((long) dbObj.get("createdDate")));

        receipt.setImage(getImage(dbObj));

        return receipt;
    }

    private model.Image getImage(DBObject dbObj) {
        
        BasicDBObject dbImage = (BasicDBObject) dbObj.get("image");        
        model.Image image = new model.Image();
        
        image.setHeight((int) dbImage.get("height"));
        image.setWidth((int) dbImage.get("width"));
        image.setFormat((String) dbImage.get("format"));
        try {
            image.setByteArray((String) dbImage.get("64bit"));
        } catch (Base64DecodingException b64) {
            //TODO: Logging
        }
        
        return image; 
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
