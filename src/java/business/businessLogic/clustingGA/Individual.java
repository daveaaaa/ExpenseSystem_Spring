/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.clustingGA;

import business.businessModel.ReceiptType;

/**
 *
 * @author david
 */
public class Individual {

    private double total;
    private int numberOfItems;
    private ReceiptType receiptType;
    private boolean changable;

    public boolean isChangable() {
        return changable;
    }

    public void setChangable(boolean changable) {
        this.changable = changable;
    }

    
    
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public ReceiptType getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(ReceiptType receiptType) {
        this.receiptType = receiptType;
    }

    public int[] getIndividual(){
        int[] indiv = new int[5];
        
        indiv[0] = numberOfItems;
        indiv[1] = numberOfItems;
        indiv[2] = (int) total * 100; //44.33 == 4433
        indiv[3] = (int) total * 100;
        indiv[4] = receiptType.getValue(); 
        return indiv;
    }
    
    
    
    
    
    public String toJson() {
        String json = "{\"total\":\"%d\", \"items\":\"%i\", \"type\":\"%i\"}";
        json = String.format(json, total, numberOfItems, receiptType.getValue());

        return json;

    }
}
