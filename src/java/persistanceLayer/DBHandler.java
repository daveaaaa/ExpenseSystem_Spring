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

    public void createReceipt();

    public void updateReceipt(Receipt reciept);

    public ArrayList<Receipt> findReceipt();

    public Receipt getReceipt(int userID, Date startDate, Date endDate);

    public void createUser();

    public void updateUser(User user);

    public ArrayList<User> findUser();

    public User getUser(String username, String password);

}
