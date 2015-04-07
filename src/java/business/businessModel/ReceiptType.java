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
public enum ReceiptType {

    NONE(0),
    SUBSISTANCE(1),
    TRANSPORT(2),
    OTHER(3);

    private int value;

    ReceiptType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
