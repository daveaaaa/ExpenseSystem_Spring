/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.parseImage;

import business.businessModel.Receipt;
import org.w3c.dom.Document;

/**
 *
 * @author david
 */
public interface ParseImage {

    public Document parseImage(Receipt receipt);

}
