/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.parseImage.AORC;

import business.businessLogic.parseImage.ParseImage;
import business.businessModel.Item;
import business.businessModel.Receipt;
import business.businessModel.ReceiptItem;
import com.asprise.ocr.Ocr;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author david
 */
public class ParseImageAORC implements ParseImage {

    private Ocr ocr;
    private File file;
    private String xml;
    private Document doc;

    @Override
    public Receipt parseImage(Receipt receipt) {

        try {
            setupOCR();
            createFile(receipt);
            createXML();
            loadXMLFromString();
            createReceiptItem();
            cleanUp();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            //Log Error
        }
        return receipt;
    }

    private void setupOCR() {
        try {
            if (Ocr.isSetupRequired()) {
                Ocr.setUp();
            }
            ocr = new Ocr();
            if (!ocr.isEngineRunning()) {
                ocr.startEngine("eng", Ocr.SPEED_SLOW);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    private File createFile(Receipt receipt) throws IOException {
        String format = receipt.getImage().getFileFormat();
        file = File.createTempFile(receipt.getReceiptID(), format);

        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(receipt.getImage().getByteArray());
        }
        return file;
    }

    private void createXML() {
        xml = ocr.recognize(new File[]{file}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_XML);
    }

    private void loadXMLFromString() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        doc = builder.parse(is);
    }

    private ReceiptItem createReceiptItem() throws Exception {
        ReceiptItem receiptItem = new ReceiptItem();

        Element docElem = doc.getDocumentElement();

        NodeList nl = docElem.getElementsByTagName("block");

        for (int i = 0; i != nl.getLength(); i++) {
            Node node = nl.item(i);
            String text = node.getNodeName();

            Attr attr = (Attr) node.getAttributes().getNamedItem("type");
            if ((attr != null) & (attr.getNodeValue().equals("text"))) {
                Node child = node.getChildNodes().item(0);

                if (!child.getNodeValue().contains("CDATA")) {
                    Item item = new Item();
                    item.setXML(child.getNodeValue());
                    receiptItem.addItem(item);
                }

            }
        }

        return receiptItem;
    }

    private void cleanUp() {
        file.delete();
        ocr.stopEngine();
    }
}
