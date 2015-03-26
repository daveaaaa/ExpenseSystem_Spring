/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.parseImage;

/**
 *
 * @author david
 */
public class ParseXMLStringReplace {
     public static String makeZeros(String value) {
        value = value.replaceAll("D", "0");
        value = value.replaceAll("O", "0");
        value = value.replaceAll("o", "0");
        return value;
    }

    public static String makeOnes(String value) {
        value = value.replaceAll("l", "1");
        value = value.replaceAll("L", "1");
        value = value.replaceAll("i", "1");
        value = value.replaceAll("I", "1");
        return value;
    }

}
