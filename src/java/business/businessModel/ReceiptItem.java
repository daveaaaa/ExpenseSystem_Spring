/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessModel;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class ReceiptItem {
    
    private ArrayList<Item> items;
    private int lastItemID;
    
    public ReceiptItem() {
        items = new ArrayList<>();
        lastItemID = -1;    // allows for 0 indexing
    }
    
    public void addItem(Item item) {
        lastItemID++;
        item.setID(lastItemID);
        items.add(item);
    }
    
    public ArrayList<Item> getAllItems() {
        return items;
    }
    
    public void setItems(ArrayList<Item> items) {
        items = ensureItemsHasAnID(items);
        this.items = items;
    }
    
    private ArrayList<Item> ensureItemsHasAnID(ArrayList<Item> items) {
        int maxID = -1;
        
        ArrayList<Integer> itemsWithoutIDs = new ArrayList<>();
        
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).getID() == -1) {
                itemsWithoutIDs.add(i);
            } else {
                if (items.get(i).getID() > maxID) {
                    maxID = items.get(i).getID();
                }
            }
        }
        
        for (int i = 0; i != itemsWithoutIDs.size(); i++) {
            maxID++;
            items.get(itemsWithoutIDs.get(i)).setID(maxID);
        }
        
        return items;
    }
    
    public ArrayList<Item> getMerchants() {
        ArrayList<Item> merchants = findType(ItemType.Merchant);
        return merchants;
    }
    
    public ArrayList<Item> getItems() {
        ArrayList<Item> itemList = findType(ItemType.Item);
        return itemList;
    }
    
    public ArrayList<Item> getTotal() {
        ArrayList<Item> itemList = findType(ItemType.Total);
        return itemList;
    }
    
    private ArrayList<Item> findType(ItemType type) {
        ArrayList<Item> typeList = new ArrayList<>();
        for (Item item : items) {
            if (item.getType() == type) {
                typeList.add(item);
            }
        }
        return typeList;
    }
    
    public String getItemsAsJSON() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("[");
        for (Item item : items) {
            sb.append(item.getJSON());
            sb.append(",");
        }
        sb.delete((sb.length() - 1), sb.length());
        sb.append("]");
        
        return sb.toString();
    }
}
