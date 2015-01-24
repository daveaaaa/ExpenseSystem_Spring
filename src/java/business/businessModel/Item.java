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

    private String name;
    private double price;
    private int quantity;
    private double total;

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
    
    public void setTotal(String total){
        this.total = Double.parseDouble(total); 
    }

    public String getJSON(){
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
