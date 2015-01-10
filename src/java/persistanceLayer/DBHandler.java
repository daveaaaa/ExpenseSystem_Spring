/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistanceLayer;

import java.util.ArrayList;
import java.util.Date;
import model.Receipt;
import model.User;

/**
 *
 * @author david
 */
public interface DBHandler {

    public Receipt createReceipt(Receipt reciept);

    public void updateReceipt(Receipt reciept);

    public ArrayList<Receipt> findReceipt();

    public Receipt getReceipt(String userID, Date startDate, Date endDate);

    public Receipt getReceipt(String receiptID); 
    
    public void createUser(User user);

    public void updateUser(User user);

    public ArrayList<User> findUser();

    public User getUser(String username, String password);

}
