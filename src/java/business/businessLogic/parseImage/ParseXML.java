/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.parseImage;

import business.businessModel.Item;
import business.businessModel.Receipt;
import business.businessModel.ReceiptItem;
import java.util.ArrayList;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author david
 */
public class ParseXML {

    public static Receipt parseXML(Document doc, business.businessModel.Receipt receipt) {
        ReceiptItem receiptItem = new ReceiptItem();

        Element docElem = doc.getDocumentElement();
        NodeList nl = docElem.getElementsByTagName("block");

        ArrayList<Node> validNodes = getValidNodes(nl);

        ArrayList<Item> potentialMerchants = ParseXMLFindMerchant.getMerchantName(validNodes, receipt);
        ArrayList<Item> potentialTotals = ParseXMLFindTotal.getTotal(validNodes);
        
        return receipt;
    }

    private static ArrayList<Node> getValidNodes(NodeList nl) {
        ArrayList<Node> validNodes = new ArrayList<>();

        for (int i = 0; i != nl.getLength(); i++) {
            Node node = nl.item(i);
            if (isValidNode(node)) {
                validNodes.add(node);
            }
        }
        return validNodes;
    }

    /**
     * If is a valid node
     */
    private static boolean isValidNode(Node node) {
        boolean isValidNode = false;
        Attr attr = (Attr) node.getAttributes().getNamedItem("type");

        if ((attr != null) & (attr.getNodeValue().equals("text"))) {
            Node child = node.getChildNodes().item(0);

            if ((!child.getNodeValue().contains("CDATA")) || (child.getNodeValue().trim().length() > 0)) {
                isValidNode = true;
            }
        }
        return isValidNode;
    }

}
