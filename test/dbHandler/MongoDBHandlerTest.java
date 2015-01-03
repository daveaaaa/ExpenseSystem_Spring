/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbHandler;

import java.io.File;
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

/**
 *
 * @author david
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
     * Test of save method, of class MongoDBHandler.
     */
    @Test
    public void test1_SaveInformation() {
        System.out.println("save information");
        ArrayList<Tuplet> information = null;
        String collection = "";
        MongoDBHandler instance = null;
        instance.save(information, collection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class MongoDBHandler.
     */
    @Test
    public void test2_ListInformation() {
        System.out.println("list");
        String collection = "";
        MongoDBHandler instance = null;
        instance.list(collection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class MongoDBHandler.
     */
    @Test
    public void test3_SaveImage() throws Exception {
        System.out.println("saveImage");
        String filename = "";
        File image = null;
        String collection = "";
        MongoDBHandler instance = null;
        instance.saveImage(filename, image, collection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public void test4_getImage() throws Exception {
        System.out.println("getImage");
        String filename = "";
        File image = null;
        String collection = "";
        MongoDBHandler instance = null;
        instance.getImage("","");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
