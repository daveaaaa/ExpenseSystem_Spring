/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseAccess.mongoDB;

import business.businessModel.Item;
import business.businessModel.ItemType;
import business.businessModel.Receipt;
import business.businessModel.SecurityGroup;
import business.businessModel.User;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class MongoDBHandlerTest {

    public MongoDBHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createReceipt method, of class MongoDBHandler.
     */
    @Test
    public void createUser() {
        String dbHost = "127.0.0.1";
        String dbName = "ExpenseSystem";
        String dbUsername = "app";
        String dbPassword = "app";
        MongoDBHandler handler = null;
        business.businessModel.User user = new business.businessModel.User();

        user.setPassword("password");
        try {
            handler = new MongoDBHandler(dbHost, dbName, dbUsername, dbPassword);
        } catch (Exception ex) {
            fail("DB Connection Exception");
        }
        
        user.setUsername("adder");
        user.setSecurityGroup(SecurityGroup.ReceiptProvider);
        handler.createUser(user);
        
        user.setUsername("admin");
        user.setSecurityGroup(SecurityGroup.Admin);
        
        handler.createUser(user);
        
    }

//    @Test
    public void testFindUser() {
        String dbHost = "127.0.0.1";
        String dbName = "ExpenseSystem";
        String dbUsername = "app";
        String dbPassword = "app";
        MongoDBHandler handler = null;

        business.businessModel.User oldUser = new business.businessModel.User();
        oldUser.setUsername("test1");
        oldUser.setPassword("password");
        oldUser.setSecurityGroup(SecurityGroup.ReceiptProvider);
        try {
            handler = new MongoDBHandler(dbHost, dbName, dbUsername, dbPassword);
        } catch (Exception ex) {
            fail("DB Connection Exception");
        }

        //Write to db
        handler.createUser(oldUser);

        //Pull from db
        User foundUser = handler.getUser(oldUser.getUsername(), oldUser.getPassword());

        System.out.print(foundUser.toString());

    }

    //   @Test
    public void testFindNoneUser() {
        String dbHost = "127.0.0.1";
        String dbName = "ExpenseSystem";
        String dbUsername = "app";
        String dbPassword = "app";
        MongoDBHandler handler = null;

        business.businessModel.User oldUser = new business.businessModel.User();
        oldUser.setUsername("admin");
        oldUser.setPassword("password");
        oldUser.setSecurityGroup(SecurityGroup.ReceiptProvider);
        try {
            handler = new MongoDBHandler(dbHost, dbName, dbUsername, dbPassword);
        } catch (Exception ex) {
            fail("DB Connection Exception");
        }

        //Write to db
        handler.createUser(oldUser);

        //Pull from db
        User foundUser = handler.getUser("failingTest", "");

        System.out.print(foundUser.toString());
    }

    // @Test
    public void deleteTest() {
        String dbHost = "127.0.0.1";
        String dbName = "ExpenseSystem";
        String dbUsername = "app";
        String dbPassword = "app";
        MongoDBHandler handler = null;

        business.businessModel.User oldUser = new business.businessModel.User();
        oldUser.setUsername("admin");
        oldUser.setPassword("password");
        oldUser.setSecurityGroup(SecurityGroup.ReceiptProvider);
        try {
            handler = new MongoDBHandler(dbHost, dbName, dbUsername, dbPassword);
        } catch (Exception ex) {
            fail("DB Connection Exception");
        }

        //Write to db
        handler.createUser(oldUser);

        oldUser = handler.getUser(oldUser.getUsername(), oldUser.getPassword());

        //Delete from db
        handler.deleteUser(oldUser.getUserID());

    }

    //   @Test
    public void listAllReceipts() {
        String dbHost = "127.0.0.1";
        String dbName = "ExpenseSystem";
        String dbUsername = "app";
        String dbPassword = "app";
        MongoDBHandler handler = null;
        try {
            handler = new MongoDBHandler(dbHost, dbName, dbUsername, dbPassword);
        } catch (Exception ex) {
            fail("DB Connection Exception");
        }

        ArrayList<business.businessModel.Receipt> reciepts = handler.listAllReceipts();

        for (business.businessModel.Receipt receipt : reciepts) {
            System.out.println(receipt.toString());
        }

    }

    @Test
    public void listAllReceiptsOfUser() {
        String dbHost = "127.0.0.1";
        String dbName = "ExpenseSystem";
        String dbUsername = "app";
        String dbPassword = "app";
        MongoDBHandler handler = null;
        String userID = "5506f89587f28395aef5f1af";
        try {
            handler = new MongoDBHandler(dbHost, dbName, dbUsername, dbPassword);
        } catch (Exception ex) {
            fail("DB Connection Exception");
        }

        ArrayList<business.businessModel.Receipt> reciepts = handler.listReceipts(userID);

        for (business.businessModel.Receipt receipt : reciepts) {
            System.out.println(receipt.toString());
        }
    }

    @Test
    public void updateReceipt() {
        String dbHost = "127.0.0.1";
        String dbName = "ExpenseSystem";
        String dbUsername = "app";
        String dbPassword = "app";
        MongoDBHandler handler = null;

        try {
            handler = new MongoDBHandler(dbHost, dbName, dbUsername, dbPassword);
        } catch (Exception ex) {
            fail("DB Connection Exception");
        }

        Receipt receipt = new Receipt();
        receipt.setUserID("UPDATE_TEST1234");

        receipt = handler.createReceipt(receipt);

        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i != 3; i++) {
            Item item = new Item();
            item.setName("Merchant" + i);
            item.setType(ItemType.Merchant);
            items.add(item);
        }

        for (int i = 0; i != 3; i++) {
            Item item = new Item();
            item.setName("Item" + i);
            item.setPrice(10 * i);
            item.setQuantity(1);
            item.setType(ItemType.Item);
            items.add(item);
        }

        receipt.getReceiptItems().setItems(items);

        handler.updateReceipt(receipt);
        
        
        for(Item item : receipt.getReceiptItems().getItems()){
            item.setPrice(item.getPrice() + 15);
        }
        
         for (int i = 0; i != 3; i++) {
            Item item = new Item();
            item.setName("Item" + i);
            item.setPrice(10 * i);
            item.setQuantity(1);
            item.setType(ItemType.Item);
            receipt.getReceiptItems().addItem(item);
        }
        
        handler.updateReceipt(receipt);
    }
}
