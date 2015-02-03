/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.parseImage.AORC;

import business.businessLogic.parseImage.ParseImage;
import business.businessModel.Receipt;
import business.businessModel.ReceiptItem;
import com.asprise.ocr.Ocr;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author david
 */
public class ParseImageAORC implements ParseImage {

    private Ocr ocr;
    private File file;
    private String xml;

    @Override
    public Receipt parseImage(Receipt receipt) {

        try {
            setupOCR();
            createFile(receipt);
            createXML();
            loadXMLFromString();
            receipt.setCurrentReceiptItem(createReceiptItem());
            cleanUp();
            } catch (Exception ex) {
            ex.printStackTrace(System.err);
            //Log Error
        }
        return receipt;
    }

    private void setupOCR() {
        try{
        Ocr.setUp();
        ocr = new Ocr();
        ocr.startEngine("eng", Ocr.SPEED_SLOW);
        } catch (Exception ex){
            ex.printStackTrace(System.err);
        }
    }
    

    private File createFile(Receipt receipt) throws IOException {
        String format = receipt.getImage().getFormat();
        file = File.createTempFile(receipt.getReceiptID(), format);
        
        try(FileOutputStream out = new FileOutputStream(file)){
            out.write(receipt.getImage().getByteArray());
        } 
        return file;
    }

    private void createXML() {
        xml = ocr.recognize(new File[]{file}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_XML);
    }

    private Document loadXMLFromString() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
    
    private ReceiptItem createReceiptItem(){
        ReceiptItem receiptItem = new ReceiptItem(); 
        
        
        
        return receiptItem;
    }
    
    private void cleanUp(){
        file.delete();
    }
}
