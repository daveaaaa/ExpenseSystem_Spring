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
        int newValue = 0;

        switch (position) {
            case MIN_ITEMS:
                newValue = doCreep(GA.MIN_ITEMS, individual[MAX_ITEMS], individual[position]);
                break;
            case MAX_ITEMS:
                newValue = doCreep(individual[MIN_ITEMS], GA.MAX_ITEMS, individual[position]);
                break;
            case MIN_TOTAL:
                newValue = doCreep(GA.MIN_TOTAL, individual[MAX_TOTAL], individual[position]);
                break;
            case MAX_TOTAL:
                newValue = doCreep(individual[MIN_TOTAL], GA.MAX_TOTAL, individual[position]);
                break;
            case TYPE:
                doTypeChange();
                break;
        }

        if (position != TYPE) {
            individual[position] = newValue;
        }

    }

    private void doTypeChange() {
        double rand = Math.random();
        if (rand > 0.66) {
            individual[TYPE] = ReceiptType.OTHER.getValue();
        } else if (rand > 0.33) {
            individual[TYPE] = ReceiptType.SUBSISTANCE.getValue();
        } else {
            individual[TYPE] = ReceiptType.TRANSPORT.getValue();
        }
    }

    private int doCreep(int minValue, int maxValue, int oldValue) {
        int newValue;
        if (Math.random() > 0.5) {
            newValue = creepPositive(maxValue, oldValue);
        } else {
            newValue = creepNegitive(minValue, oldValue);
        }
        return newValue;
    }

    private int creepPositive(int maxValue, int oldValue) {
        int newValue = 0;
        if ((oldValue + 1) > maxValue) {
            newValue = oldValue;
        } else {
            newValue = oldValue + 1;
        }
        return newValue;
    }

    private int creepNegitive(int minValue, int oldValue) {
        int newValue = 0;
        if (minValue > (oldValue - 1)) {
            newValue = oldValue;
        } else {
            newValue = oldValue - 1;
        }

        return newValue;
    }

    public void setIndividual(int[] individual) {
        this.individual = individual;
    }

    public boolean matches(Individual data) {
        boolean isMatch = false;

        if (betweenItems(data.getNumberOfItems())
                && (betweenTotal(data.getTotal()))
                && (sameType(data.getReceiptType()))) {
            isMatch = true;
        }
        return isMatch;
    }

    private boolean betweenItems(int numberOfItems) {
        boolean isBetweenItems = false;

        if ((numberOfItems >= individual[MIN_ITEMS])
                && (individual[MAX_ITEMS] >= numberOfItems)) {
            isBetweenItems = true;
        }
        return isBetweenItems;
    }

    private boolean betweenTotal(double total) {
        boolean isBetweenTotal = false;
        int intTotal = (int) total * 100; //£44.33 == 4433

        if ((intTotal >= individual[MIN_TOTAL])
                && (individual[MAX_TOTAL] >= intTotal)) {
            isBetweenTotal = true;
        }

        return isBetweenTotal;
    }

    private boolean sameType(ReceiptType receiptType) {
        boolean isSameType = false;

        if (individual[TYPE] == receiptType.getValue()) {
            isSameType = true;
        }

        return isSameType;
    }
}
