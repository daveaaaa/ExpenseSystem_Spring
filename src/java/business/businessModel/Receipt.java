package business.businessModel;

import java.io.File;
import java.util.Date;
import business.businessModel.ReceiptImage;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Receipt {

    private String receiptID;
    private ReceiptImage image;
    private String userID;
    private java.util.Date createdOn;
    private ArrayList<ReceiptItem> receiptItems;
    private int currentReceiptItem;

    public Receipt() {
        receiptID = "";
        userID = "";
        createdOn = new java.util.Date();
        image = new ReceiptImage();
        receiptItems = new ArrayList<>();
    }

    public Receipt(String userID) {
        this.userID = userID;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public ReceiptImage getImage() {
        return image;
    }

    public void setImage(ReceiptImage image) {
        this.image = image;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setCreatedDate(Date createdOn) {
        this.createdOn = createdOn;
    }

    public ReceiptItem getCurrentReceiptItem() {
        return receiptItems.get(currentReceiptItem);
    }

    public ArrayList<ReceiptItem> getReceiptItems() {
        return receiptItems;
    }

    public void setCurrentReceiptItem(ReceiptItem createReceiptItem) {
        if (!receiptItems.contains(createReceiptItem)) {
            receiptItems.add(createReceiptItem);
        }
        currentReceiptItem = receiptItems.indexOf(createReceiptItem);
    }
}
