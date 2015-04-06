package databaseAccess.mongoDB;

import business.businessModel.Item;
import com.mongodb.*;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import business.businessModel.Receipt;
import business.businessModel.User;
import org.bson.types.ObjectId;
import databaseAccess.DBHandler;

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

    /**
     * **********************
     *
     * helper methods
     *
     */
    private DBCollection getReceiptCollection() {
        return db.getCollection(RECIEPT_COLLECTION);
    }

    private DBCollection getUserCollection() {
        return db.getCollection(USER_COLLECTION);
    }

    private BasicDBObject getDocumentFromReceipt(Receipt receipt) {
        return new BasicDBObject();
    }

    /**
     * **********************
     *
     * receipt methods
     *
     */
    @Override
    public Receipt createReceipt(Receipt reciept) {
        reciept = saveReceipt(reciept);
        return reciept;
    }

    private Receipt saveReceipt(Receipt receipt) {
        DBCollection table = getReceiptCollection();
        BasicDBObject document = getDocumentFromReceipt(receipt);

        document.put("userID", receipt.getUserID());
        document.put("createdDate", new java.util.Date().getTime());
        document.put("image", saveImage(receipt));

        table.insert(document);
        String id = document.getString("_id");
        receipt.setReceiptID(id);

        return receipt;
    }

    private BasicDBObject saveImage(Receipt receipt) {

        BasicDBObject metaData = new BasicDBObject();

        metaData.put("64bit", receipt.getImage().getBase64());
        metaData.put("format", receipt.getImage().getFormat());
        metaData.put("height", receipt.getImage().getHeight());
        metaData.put("width", receipt.getImage().getWidth());

        return metaData;

    }

    @Override
    public ArrayList<Receipt> listAllReceipts() {
        ArrayList<business.businessModel.Receipt> reciepts = new ArrayList<>();

        DBCollection table = getReceiptCollection();
        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            BasicDBObject dbObj = (BasicDBObject) cursor.next();
            business.businessModel.Receipt receipt = populateReceipt(dbObj);
            reciepts.add(receipt);
        }

        return reciepts;
    }

    @Override
    public ArrayList<Receipt> listReceipts(String userID) {
        ArrayList<business.businessModel.Receipt> reciepts = new ArrayList<>();
        BasicDBObject query = new BasicDBObject();
        query.put("userID", userID);

        DBCollection table = getReceiptCollection();
        DBCursor cursor = table.find(query);
        while (cursor.hasNext()) {
            BasicDBObject dbObj = (BasicDBObject) cursor.next();
            business.businessModel.Receipt receipt = populateReceipt(dbObj);
            reciepts.add(receipt);
        }

        return reciepts;

    }

    @Override
    public void updateReceipt(Receipt reciept) {
        BasicDBObject query = new BasicDBObject();
        DBCollection table = getReceiptCollection();

        query.put("_id", new ObjectId(reciept.getReceiptID()));

        BasicDBObject receipt = new BasicDBObject();

        receipt.append("$set", new BasicDBObject().append("userID", reciept.getUserID()));
        receipt.append("$set", new BasicDBObject().append("receiptDate", reciept.getReceiptDate()));
        receipt.append("$set", new BasicDBObject().append("total", reciept.getTotal()));
        receipt.append("$set", new BasicDBObject().append("receiptItems", setItems(reciept.getReceiptItems().getAllItems())));
        table.update(query, receipt);

    }

    private DBObject setItems(ArrayList<Item> items) {
        BasicDBList receiptItems = new BasicDBList();
        for (Item item : items) {
            BasicDBObject itemDocument = new BasicDBObject();
            itemDocument.append("id", item.getID());
            itemDocument.append("name", item.getName());
            itemDocument.append("type", item.getType().getValue());
            itemDocument.append("price", item.getPrice());
            itemDocument.append("total", item.getTotal());
            itemDocument.append("quantity", item.getQuantity());
            itemDocument.append("isIncluded".toLowerCase(), item.isIncluded());
            itemDocument.append("xml", item.getXML());
            receiptItems.add(itemDocument);
        }

        return receiptItems;
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
    public business.businessModel.Receipt getReceipt(String receiptID) {
        DBCollection table = getReceiptCollection();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(receiptID));
        DBObject dbObj = table.findOne(query);

        return populateReceipt(dbObj);
    }

    private business.businessModel.Receipt populateReceipt(DBObject dbObject) {
        Receipt receipt = new Receipt();

        BasicDBObject dbObj = (BasicDBObject) dbObject;
        String id = dbObj.getString("_id");
        String userID = dbObj.getString("userID");
        java.util.Date createdOn = new java.util.Date((long) dbObj.get("createdDate"));

        receipt.setReceiptID(id);
        receipt.setUserID(userID);
        receipt.setCreatedDate(createdOn);
        if (dbObj.containsField("receiptItems")) {
            receipt.setReceiptItems(getReceiptItems(dbObj));
        }
        receipt.setImage(getImage(dbObj));

        return receipt;
    }

    private business.businessModel.ReceiptItem getReceiptItems(DBObject dbObj) {
        business.businessModel.ReceiptItem receiptItem = new business.businessModel.ReceiptItem();

        BasicDBList receiptItems = (BasicDBList) dbObj.get("receiptItems");

        for (int i = 0; i != receiptItems.size(); i++) {
            business.businessModel.Item item = new business.businessModel.Item();
            BasicDBObject obj = (BasicDBObject) receiptItems.get(i);

            int id = obj.getInt("id");
            double price = obj.getDouble("price");
            double total = obj.getDouble("total");
            int quantity = obj.getInt("quantity");
            String name = obj.getString("name");
            int type = obj.getInt("type");
            String xml = obj.getString("xml");
            int isIncluded = obj.getInt("isIncluded");

            item.setID(id);
            item.setPrice(price);
            item.setQuantity(quantity);
            item.setName(name);
            item.setType(type);
            item.setXML(xml);
            item.setIsIncluded(isIncluded);

            receiptItem.addItem(item);
        }

        return receiptItem;
    }

    private business.businessModel.ReceiptImage getImage(DBObject dbObj) {

        BasicDBObject dbImage = (BasicDBObject) dbObj.get("image");
        business.businessModel.ReceiptImage image = new business.businessModel.ReceiptImage();

        int height = (int) dbImage.get("height");
        int width = (int) dbImage.get("width");
        String format = (String) dbImage.get("format");
        String base64 = (String) dbImage.get("64bit");
        image.setHeight(height);
        image.setWidth(width);
        image.setFormat(format);
        try {
            image.setBase64(base64);
        } catch (Base64DecodingException b64) {
            //TODO: Logging
        }

        return image;
    }

    /**
     * **********************
     *
     * user methods
     *
     */
    @Override
    public void createUser(User user) {
        DBCollection table = getUserCollection();
        BasicDBObject document = new BasicDBObject();

        document.put("username", user.getUsername());
        document.put("password", user.getPassword());
        document.put("securitygroup", user.getSecurityGroup().getValue());
        table.insert(document);

    }

    @Override
    public void updateUser(User user) {

        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(user.getUserID()));

        DBCollection table = getUserCollection();
        BasicDBObject userDocument = new BasicDBObject();

        userDocument.append("$set", new BasicDBObject().append("username", user.getUsername()));
        userDocument.append("$set", new BasicDBObject().append("password", user.getPassword()));
        userDocument.append("$set", new BasicDBObject().append("securitygroup", user.getSecurityGroup().getValue()));

        table.update(query, userDocument);
    }

    @Override
    public ArrayList<User> findUser() {
        BasicDBObject query = new BasicDBObject();
        ArrayList<User> userList = new ArrayList<>();

        DBCollection table = getUserCollection();
        DBCursor cursor = table.find(query);

        while (cursor.hasNext()) {
            BasicDBObject dbObj = (BasicDBObject) cursor.next();
            User newUser = populateUser(dbObj);
            userList.add(newUser);
        }

        return userList;
    }

    @Override
    public void deleteUser(String userID) {
        BasicDBObject query = new BasicDBObject();

        query.put("_id", new ObjectId(userID));

        DBCollection table = getUserCollection();
        DBObject user = (DBObject) table.findOne(query);
        table.remove(user);
    }

    @Override
    public User getUser(String searchUsername, String searchPassword) {
        business.businessModel.User user;
        BasicDBObject query = new BasicDBObject();
        query.put("username", searchUsername);
        query.put("password", searchPassword);

        DBCollection table = getUserCollection();
        BasicDBObject dbObj = (BasicDBObject) table.findOne(query);
        user = populateUser(dbObj);

        return user;

    }

    @Override
    public business.businessModel.User findUser(String userID) {
        BasicDBObject query = new BasicDBObject();

        query.put("_id", new ObjectId(userID));
        DBCollection table = getUserCollection();
        BasicDBObject dbObj = (BasicDBObject) table.findOne(query);
        business.businessModel.User user = populateUser(dbObj);

        return user;
    }

    private business.businessModel.User populateUser(BasicDBObject dbObj) {
        User user = new User();
        try {
            String id = (String) dbObj.getString("_id");
            String username = (String) dbObj.getString("username");
            String password = (String) dbObj.getString("password");
            String securityGroup = (String) dbObj.getString("securitygroup");

            user.setUserID(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setSecurityGroup(securityGroup);
        } catch (NullPointerException npe) {
            //TODO: Log
        }
        return user;
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
