/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author david
 */
public class Tuplet {

    private String key;
    private String attribute;

    public Tuplet() {
        this("", "");
    }

    public Tuplet(String key, String attribute) {
        this.key = key;
        this.attribute = attribute;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
