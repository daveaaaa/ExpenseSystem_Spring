package business.businessModel;

import java.io.File;
import java.util.Date;
import business.businessModel.ReceiptImage;
import business.businessModel.User; 
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Receipt {

    private String receiptID;
    private ReceiptImage image;
    private String userID;
    private User user;
    private java.util.Date createdOn;
    private java.util.Date receiptDate;
    private ReceiptItem receiptItems;
    private int currentReceiptItem;
    private double total;

    public Receipt() {
        receiptID = "";
        userID = "";
        createdOn = new java.util.Date();
        image = new ReceiptImage();
        receiptItems = new ReceiptItem();
        receiptDate = new java.util.Date();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCurrentReceiptItem() {
        return currentReceiptItem;
    }

    public void setCurrentReceiptItem(int currentReceiptItem) {
        this.currentReceiptItem = currentReceiptItem;
    }
    

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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

//    public ReceiptItem getCurrentReceiptItem() {
//        return receiptItems.get(currentReceiptItem);
//    }
    
    public void setReceiptItems(ReceiptItem receiptItems) {
        this.receiptItems = receiptItems;
    }

    public ReceiptItem getReceiptItems() {
        return receiptItems;
    }

//    public void setCurrentReceiptItem(ReceiptItem createReceiptItem) {
//        if (!receiptItems.contains(createReceiptItem)) {
//            receiptItems.add(createReceiptItem);
//        }
//        currentReceiptItem = receiptItems.indexOf(createReceiptItem);
//    }
}
