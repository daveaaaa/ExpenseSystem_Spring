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

    public static final int MIN_ITEMS = 0;
    public static final int MAX_ITEMS = 1;
    public static final int MIN_TOTAL = 2;
    public static final int MAX_TOTAL = 3;
    public static final int TYPE = 5;

    private double total;
    private int numberOfItems;
    private ReceiptType receiptType = ReceiptType.NONE;
    private boolean changable = true;
    private int[] individual;

    public Individual() {
        individual = new int[5];
    }

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
        individual[MIN_TOTAL] = (int) this.total * 100; //44.33 == 4433
        individual[MAX_TOTAL] = (int) this.total * 100;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
        individual[MIN_ITEMS] = this.numberOfItems;
        individual[MAX_ITEMS] = this.numberOfItems;

    }

    public ReceiptType getReceiptType() {
        return receiptType;

    }

    public void setReceiptType(ReceiptType receiptType) {
        this.receiptType = receiptType;
        individual[TYPE] = this.receiptType.getValue();
    }

    public int[] getIndividual() {
        return individual;
    }

    public String toJson() {
        String json = "{\"total\":\"%d\", \"items\":\"%i\", \"type\":\"%i\"}";
        json = String.format(json, total, numberOfItems, receiptType.getValue());

        return json;

    }

    public int getSize() {
        return individual.length;
    }

    public void mutate(int position) {
        switch (position) {
            case MIN_ITEMS:
                break;
            case MAX_ITEMS:
                break;
            case MIN_TOTAL:
                break;
            case MAX_TOTAL:
                break;
            case TYPE:
                break;
        }
    }

    private int creepPositive(int maxValue) {
        return 0;
    }

    private int creepNegitive(int minValue) {
        return 1;
    }

    public void setIndividual(int[] individual) {
        this.individual = individual;
    }
}
