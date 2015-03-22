/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.parseImage;

import business.businessModel.Item;
import business.businessModel.ItemType;
import business.businessModel.Receipt;
import java.util.ArrayList;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

/**
 *
 * @author david
 */
public class ParseXMLFindMerchant {

    private static ArrayList<Node> largestNodes = new ArrayList<>();
    private static int meanHeight = 0;
    private static int totalNodes = 0;
    private static Node largestNode = null;
    private static int largestHeight = 0;
    private static int largestCount = 0;
    private static int smallestHeight = 0;
    private static int smallestCount = 0;

    public static ArrayList<Item> getMerchantName(ArrayList<Node> validNodes, Receipt receipt) {
        ArrayList<Item> merchants = new ArrayList<>();
        ArrayList<Node> potentialMerchants = findPotentialMerchants(validNodes, receipt);
        for (Node node : potentialMerchants) {

            Attr attr = (Attr) node.getAttributes().getNamedItem("type");
            if ((attr != null) & (attr.getNodeValue().equals("text"))) {
                Node child = node.getChildNodes().item(0);

                if (!child.getNodeValue().contains("CDATA")) {
                    //Get rid of blank spaced nodes 
                    if (child.getNodeValue().replace(" ", "").length() > 0) {
                        Item item = new Item();
                        item.setXML(child.getNodeValue());
                        item.setType(ItemType.Merchant);
                        merchants.add(item);
                    }
                }
            }
        }

        return merchants;

    }

    private static ArrayList<Node> findPotentialMerchants(ArrayList<Node> validNodes, Receipt receipt) {
        ArrayList<Node> potentialMerchants = new ArrayList<>();

        for (Node node : validNodes) {
            merchant_heuristic1(node);

            if (merchant_heuristic2(node)) {
                if (!potentialMerchants.contains(node)) {
                    potentialMerchants.add(node);
                }
            }
            if (merchant_heuristic3(node)) {
                if (!potentialMerchants.contains(node)) {
                    potentialMerchants.add(node);
                }
            }
            if (merchant_heuristic4(node, receipt)) {
                if (!potentialMerchants.contains(node)) {
                    potentialMerchants.add(node);
                }
            }

        }
        for (Node node : largestNodes) {
            if (!potentialMerchants.contains(node)) {
                potentialMerchants.add(node);
            }
        }

        return potentialMerchants;
    }

    /**
     * *
     * Look for largest text Loop though and incorporate other heuristics for
     * optimisation speed
     */
    private static void merchant_heuristic1(Node node) {
        //1 find average text size

        Attr attr = (Attr) node.getAttributes().getNamedItem("height");
        int height = Integer.parseInt(attr.getValue());

        if (largestNode == null) {
            largestNode = node;
            largestHeight = height;
            smallestHeight = height;
            largestCount = 1;
            smallestCount = 1;
            totalNodes = 1;
        } else {
            if (largestHeight == height) {
                largestNodes.add(node);
                largestCount++;
            } else if (smallestHeight == height) {
                smallestCount++;
            } else if (smallestHeight > height) {
                smallestHeight = height;
                smallestCount = 1;
            } else if (height > largestHeight) {
                largestNode = node;
                largestHeight = height;
                largestCount = 1;
                largestNodes.clear();
                largestNodes.add(node);
            }

            totalNodes++;
            meanHeight += height;
        }

    }

    /**
     * *
     * Search for phrase "thankyou for shopping at"
     */
    private static boolean merchant_heuristic2(Node node) {
        boolean result = false;
        String searchPhrase = "thankyou for shopping at";
        if (node.getChildNodes().item(0).getNodeValue().toLowerCase().contains(searchPhrase.toLowerCase())) {
            result = true;
        }
        return result;
    }

    /**
     * *
     * Search for the word "merchant"
     */
    private static boolean merchant_heuristic3(Node node) {
        boolean result = false;
        String searchPhrase = "merchant";
        if (node.getChildNodes().item(0).getNodeValue().toLowerCase().contains(searchPhrase.toLowerCase())) {
            result = true;
        }
        return result;
    }

    /**
     * *
     * Split image into 3rds and take the line for the top third
     */
    private static boolean merchant_heuristic4(Node node, Receipt receipt) {
        boolean result = false;

        int top3rd = ((receipt.getImage().getHeight() / 3) * 2);
        int merchantCount = 5;

        Attr height = (Attr) node.getAttributes().getNamedItem("y");
        Attr count = (Attr) node.getAttributes().getNamedItem("words");

        if ((Integer.parseInt(height.getValue()) > top3rd) && (merchantCount >= Integer.parseInt(count.getValue()))) {
            result = true;
        }
        return result;

    }

}
