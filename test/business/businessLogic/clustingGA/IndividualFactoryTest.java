/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.clustingGA;

import business.businessModel.Receipt;
import databaseAccess.DBHandler;
import databaseAccess.mongoDB.MongoDBHandler;
import databaseAccess.mongoDB.MongoDBHelper;
import java.util.ArrayList;
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
public class IndividualFactoryTest {
    
    public IndividualFactoryTest() {
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

    @Test
    public void testSomeMethod() {
         ArrayList<Receipt> receipts = new ArrayList<>();
        String dbHost = "127.0.0.1";
        String dbName = "ExpenseSystem";
        String dbUsername = "app";
        String dbPassword = "app";
        MongoDBHandler handler = null; 
 
        try {
            handler = new MongoDBHandler(dbHost, dbName, dbUsername, dbPassword);
            receipts = handler.listAllReceipts();
        } catch (Exception ex) {
            fail("DB Connection Exception");
        } 
        
        ArrayList<Individual> indivi = GAFactory.populateTrainingData(receipts);
    }
    
}
