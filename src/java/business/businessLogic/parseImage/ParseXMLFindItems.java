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
public class ParseXMLFindItems {

    public static ArrayList<Item> getItems(ArrayList<Node> validNodes) {
        ArrayList<Node> totalNodes = findItems(validNodes);
        return convertToItems(totalNodes);
    }

    private static ArrayList<Item> convertToItems(ArrayList<Node> potentialItems) {
        ArrayList<Item> itemList = new ArrayList<>();
        for (Node node : potentialItems) {

            Attr attr = (Attr) node.getAttributes().getNamedItem("type");
            if ((attr != null) & (attr.getNodeValue().equals("text"))) {
                Node child = node.getChildNodes().item(0);

                if (!child.getNodeValue().contains("CDATA")) {

                    String value = child.getNodeValue();

                    Item item = new Item();
                    item.setType(ItemType.Item);
                    item = setValue(value, item);
                    item.setXML(child.getNodeValue());
                    //item.setPrice(getTotal(child.getNodeValue()));
                    itemList.add(item);
                }
            }
        }

        return itemList;
    }

    private static Item setValue(String value, Item item) {
        String name = "";
        for(String potential : value.split(" ")){
            if(potential.contains("£")){
                potential = potential.replace("£", ""); 
                potential = ParseXMLStringReplace.makeZeros(potential);
                potential = ParseXMLStringReplace.makeOnes(potential);
                
                item.setPrice(potential);
            } else {
                name += potential + " ";
            }
            
            
        }
        item.setName(name);

        return item;
    }

    private static ArrayList<Node> findItems(ArrayList<Node> validNodes) {
        ArrayList<Node> potentialItems = new ArrayList<Node>();
        for (Node node : validNodes) {
            if (total_heuristic1(node)) {
                potentialItems.add(node);
            }
        }
        return potentialItems;
    }

    /**
     * look for a £ sign within the node
     *
     *
     */
    private static boolean total_heuristic1(Node node) {
        boolean result = false;
        String searchPhrase = "£".toLowerCase();
        String value = node.getChildNodes().item(0).getNodeValue().toLowerCase();
        if (value.contains(searchPhrase)) {
            result = true;
        }
        return result;
    }
}
