package persistanceLayer;

import java.util.ArrayList;
import java.util.Date;
import model.Receipt;
import model.User;

/**
 *
 * @author david
 */
public class NullDBHandler implements DBHandler{

    @Override
    public void createReceipt(Receipt reciept) {
        // do nothing
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
    public Receipt getReceipt(int userID, Date startDate, Date endDate) {
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
    
}
