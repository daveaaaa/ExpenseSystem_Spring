/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessModel;

/**
 *
 * @author david
 */
public class Item {

    private int ID;
    private String name;
    private double price;
    private int quantity;
    private double total;
    private String XML;
    private boolean isIncluded;
    private ItemType type;

    public Item() {
        isIncluded = true;
        ID = -1; 
    }
    
    public Item(String json){
        
    }

    public int isIncluded() {
        int included = 0;
        if (isIncluded) {
            included = 1;
        }
        return included;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
    
    public void setType(int type){
        for(ItemType iType : ItemType.values()){
            if(iType.getValue() == type){
                this.type = iType;
                break;
            }
        }
    }

    public boolean isIsIncluded() {
        return isIncluded;
    }

    public void setIsIncluded(boolean isIncluded) {
        this.isIncluded = isIncluded;
    }

    public void setIsIncluded(int included)  {
        if (included == 1) {
            isIncluded = true;
        } else if (included == 0) {
            isIncluded = false;
        } 
    }

    public String getXML() {
        return XML;
    }

    public void setXML(String XML) {
        this.XML = XML;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int numberOfItems) {
        this.quantity = numberOfItems;
    }

    public void setQuantity(String numberOfItems) {
        this.quantity = Integer.parseInt(numberOfItems);
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setTotal(String total) {
        this.total = Double.parseDouble(total);
    }

    public String getJSON() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append("\"name\":");
        sb.append("\"");
        sb.append(name);
        sb.append("\"");
        sb.append(",");
        sb.append("\"price\":");
        sb.append(price);
        sb.append("\"");
        sb.append(",");
        sb.append("\"quantity\":");
        sb.append("\"");
        sb.append(quantity);
        sb.append("\"");
        sb.append(",");
        sb.append("\"total\":");
        sb.append("\"");
        sb.append(total);
        sb.append("\"");
        sb.append("}");

        return sb.toString();
    }

}
