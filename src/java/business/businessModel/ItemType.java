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
public enum ItemType {
    None(0),
    Merchant(1),
    Address(2),
    Total(3),
    Item(4);

    private int value;

    ItemType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
