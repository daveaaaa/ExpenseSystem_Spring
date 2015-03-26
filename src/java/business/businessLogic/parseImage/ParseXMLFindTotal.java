/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.parseImage;

import business.businessModel.Item;
import business.businessModel.ItemType;
import java.util.ArrayList;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

/**
 *
 * @author david
 */
public class ParseXMLFindTotal {

    public static ArrayList<Item> getTotal(ArrayList<Node> validNodes) {
        ArrayList<Node> totalNodes = findTotal(validNodes);
        return convertToItem(totalNodes);
    }

    private static ArrayList<Item> convertToItem(ArrayList<Node> totalNodes) {
        ArrayList<Item> totalItems = new ArrayList<>();

        for (Node node : totalNodes) {

            Attr attr = (Attr) node.getAttributes().getNamedItem("type");
            if ((attr != null) & (attr.getNodeValue().equals("text"))) {
                Node child = node.getChildNodes().item(0);

                if (!child.getNodeValue().contains("CDATA")) {

                    Item item = new Item();
                    item.setType(ItemType.Total);
                    item.setXML(child.getNodeValue());
                    item.setPrice(getTotal(child.getNodeValue()));
                    totalItems.add(item);
                }
            }
        }
        return totalItems;
    }

    private static double getTotal(String xml) {
        double total = 0.0;

        for (String value : xml.split(" ")) {
            try {
                value = value.replace("£", "");
                value = StringReplace.makeZeros(value);
                value = StringReplace.makeOnes(value);
                total = Double.parseDouble(value);
            } catch (NumberFormatException nfe) {
                //DO NOTHING
            }
        }

        return total;
    }

    private static ArrayList<Node> findTotal(ArrayList<Node> validNodes) {
        ArrayList<Node> potentialTotals = new ArrayList<Node>();
        for (Node node : validNodes) {
            if (total_heuristic1(node)) {
                potentialTotals.add(node);
            }
        }

        return potentialTotals;
    }

    /**
     * *
     * find if the word total is in the sentence
     */
    private static boolean total_heuristic1(Node node) {
        boolean result = false;
        String searchPhrase = "total".toLowerCase();
        String notWanted = "sub".toLowerCase();
        String value = node.getChildNodes().item(0).getNodeValue().toLowerCase();
        if (value.contains(searchPhrase)) {
            result = true;
        }
        if ((value.contains(notWanted)) && (result == true)) {
            result = false;
        }
        return result;
    }

}
