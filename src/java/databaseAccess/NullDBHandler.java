package databaseAccess;

import java.util.ArrayList;
import java.util.Date;
import business.businessModel.Receipt;
import business.businessModel.User;

/**
 *
 * @author david
 */
public class NullDBHandler implements DBHandler {

    @Override
    public void deleteUser(String userID) {

    }

    @Override
    public business.businessModel.User findUser(String userID) {
        return new business.businessModel.User();
    }

    @Override
    public Receipt createReceipt(Receipt reciept) {
        return new Receipt();
    }

    @Override
    public void updateReceipt(Receipt reciept) {
        // do nothing
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

    @Override
    public ArrayList<Receipt> listAllReceipts() {
        return new ArrayList();
    }

    @Override
    public ArrayList<Receipt> listReceipts(String userID) {
        return new ArrayList();
    }

}
