package database;

import java.util.ArrayList;
import java.util.Date;
import business.model.Receipt;
import business.model.User;

/**
 *
 * @author david
 */
public class NullDBHandler implements DBHandler {

    @Override
    public Receipt createReceipt(Receipt reciept) {
        return new Receipt();
    }

    @Override
    public void updateReceipt(Receipt reciept) {
        // do nothing
    }

    @Override
    public ArrayList<Receipt> findReceipt() {
        return new ArrayList<>();
    }

    @Override
    public Receipt getReceipt(String userID, Date startDate, Date endDate) {
        return new Receipt();
    }

    @Override
    public void createUser(User user) {
        // do nothing
    }

    @Override
    public void updateUser(User user) {
        // do nothing
    }

    @Override
    public ArrayList<User> findUser() {
        return new ArrayList<>();
    }

    @Override
    public User getUser(String username, String password) {
        return new User();
    }

    @Override
    public Receipt getReceipt(String receiptID) {
        return new Receipt();
    }

}
