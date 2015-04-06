/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic;

import business.businessModel.SecurityGroup;
import databaseAccess.mongoDB.MongoDBHandler;
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
public class RecieptTest {

    public RecieptTest() {
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

//    @Test
//    public void parseReceipt() {
//        String dbHost = "127.0.0.1";
//        String dbName = "ExpenseSystem";
//        String dbUsername = "app";
//        String dbPassword = "app";
//        MongoDBHandler handler = null;
//
//        try {
//            handler = new MongoDBHandler(dbHost, dbName, dbUsername, dbPassword);
//            
//            ArrayList<business.businessModel.Receipt> receiptList = handler.listAllReceipts();
//            business.businessLogic.Reciept.parseReceipt(receiptList.get(0));
//
//        } catch (Exception ex) {
//            fail("DB Connection Exception");
//        }
//
//    }
    
    @Test
    public void testJSON(){
        String json = "{\"receipt\":\"5522ae6d87f22d692189aa1f\",\"item\":{\"ID\":22,\"name\":\"\",\"quantity\":\"\",\"price\":\"\",\"total\":\"\",\"type\":\"\",\"isIncluded\":\"false\"}}";
        business.businessLogic.Reciept.parseReceiptUpdateJSON(json);
    }

}
