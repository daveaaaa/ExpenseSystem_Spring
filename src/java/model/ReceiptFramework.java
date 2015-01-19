/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class ReceiptFramework {
    private ArrayList<Item> items;
    
    public ReceiptFramework(){
        items = new ArrayList<>();
    }
    
    public void addItem(Item item){
        items.add(item);
    }
    
    public String getItemsAsJSON(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("[");
        for(Item item : items){
            sb.append(item.getJSON());
            sb.append(","); 
        }
        sb.delete((sb.length() - 1), sb.length()); 
        sb.append("]");
        
        return sb.toString(); 
    }
}
