/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbHandler;

import persistanceLayer.mongoDB.MongoDBHandler;
import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import utils.Tuplet;
import com.mongodb.*;
import com.mongodb.gridfs.*;

/**
 *
 * @author david
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MongoDBHandlerTest {

    private static String host = "localhost";
    private static String db = "test";
    private static String COLLECTION_INFORMATION = "information";
    private static String COLLECTION_PHOTO = "photo";
    private static String TEST_PHOTO = "testphoto";
    private MongoDBHandler instance;

    public MongoDBHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
        try {
            MongoClient client = new MongoClient(host);
            DB database = client.getDB(db);
            database.dropDatabase();
        } catch (UnknownHostException uhe) {
            fail();
        }
    }

    @Before
    public void setUp() {
        try {
            instance = new MongoDBHandler(host, db);
        } catch (UnknownHostException uhe) {
            fail("UnknownHost");
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class MongoDBHandler.
     */
    @Test
    public void test1_SaveInformation() {
        System.out.println("save information");
        ArrayList<Tuplet> information = new ArrayList<>();

        information.add(new Tuplet("Name", "David"));
        information.add(new Tuplet("Age", "30"));

        instance.save(information, COLLECTION_INFORMATION);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class MongoDBHandler.
     */
    @Test
    public void test2_ListInformation() {
        System.out.println("list information");

        instance.list(COLLECTION_INFORMATION);
        //TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class MongoDBHandler.
     */
    @Test
    public void test3_SaveImage() throws Exception {
        System.out.println("saveImage");
        File image = new File("/home/david/Documents/Dropbox/uni/year_4/Project/Coding/ExpensesSystem_Spring/test/dbHandler/face.png");
        instance.saveImage(TEST_PHOTO, image, COLLECTION_PHOTO);
        // TODO review the generated test code and remove the default call to fail.
        ///fail("The test case is a prototype.");
    }

    @Test
    public void test4_getImage() throws Exception {
        System.out.println("getImage");
        instance.getImage(TEST_PHOTO, COLLECTION_PHOTO);
        // TODO review the generated test code and remove the default call to fail.

    }
}
