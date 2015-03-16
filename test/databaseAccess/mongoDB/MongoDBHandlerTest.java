/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseAccess.mongoDB;

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
        user.setUsername("test1");
        user.setPassword("password");
        user.setSecurityGroup(SecurityGroup.ReceiptProvider);
        try {
            handler = new MongoDBHandler(dbHost, dbName, dbUsername, dbPassword);
        } catch (Exception ex) {
            fail("DB Connection Exception");
        }

        handler.createUser(user);
    }

    @Test
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

    @Test
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
}
