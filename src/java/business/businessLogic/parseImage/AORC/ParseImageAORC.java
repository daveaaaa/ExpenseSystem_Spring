/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.parseImage.AORC;

import business.businessLogic.parseImage.ParseImage;
import business.businessModel.Receipt;
import com.asprise.ocr.Ocr;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author david
 */
public class ParseImageAORC implements ParseImage {

    private static Ocr ocr;
    private static File file;
    private static String xml;
    private static Document doc;

    private static boolean isInitialized = false;
    private static ParseImageAORC instance = null;

    protected ParseImageAORC() {
        setupOCR();
    }

    public static ParseImageAORC getInstance() {
        if (instance == null) {
            instance = new ParseImageAORC();
        }
        
        return instance; 
    }

    @Override
    public Document parseImage(Receipt receipt) {

        try {
            createFile(receipt);
            createXML();
            loadXMLFromString();
            file.delete();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            //Log Error
        }
        return doc;
    }

    public static boolean isInitialized() {
        return isInitialized;
    }

    private void setupOCR() {
        try {
            Ocr.setUp();
            ocr = new Ocr();
            ocr.startEngine("eng", Ocr.SPEED_SLOW);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    private static File createFile(Receipt receipt) throws IOException {
        String format = receipt.getImage().getFileFormat();
        file = File.createTempFile(receipt.getReceiptID(), format);

        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(receipt.getImage().getByteArray());
        }
        return file;
    }

    private static void createXML() {
        xml = ocr.recognize(new File[]{file}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_XML);
    }

    private static void loadXMLFromString() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xml));
        doc = builder.parse(inputSource);
    }

    public void cleanUp() {
        ocr.stopEngine();
    }

}
