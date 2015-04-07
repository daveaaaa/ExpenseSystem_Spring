/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.clustingGA;

import business.businessModel.Receipt;
import business.businessModel.ReceiptType;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author david
 */
public class IndividualFactory {
    
    public static ArrayList<Individual> populateTrainingData(ArrayList<Receipt> receipts){
        ArrayList<Individual> indivs = new ArrayList<>();
        
        for(Receipt receipt : receipts){
            Individual individual = new Individual();
            
            individual.setNumberOfItems(receipt.getReceiptItems().validItemCount());
            individual.setTotal(receipt.getTotal());
            individual.setReceiptType(receipt.getType());
            individual.setChangable(false);
            indivs.add(individual);
        }
        
        return indivs;
        
    }
    
    public static ArrayList<Individual> generateInitialPopulation(int size, int maxItems, int maxTotal){
        ArrayList<Individual> indivs = new ArrayList<>();
        Random rand = new Random(System.currentTimeMillis());
        
        
        for(int i = 0; i != size; i ++){
            Individual individual = new Individual();
            
            individual.setNumberOfItems(rand.nextInt(maxItems));
            individual.setTotal(rand.nextInt(maxTotal));
            individual.setReceiptType(randomReceiptType(rand));
            individual.setChangable(true);
            indivs.add(individual);
        }
        
        return indivs;
    }
    
    private static ReceiptType randomReceiptType(Random rand){
        int typeID = rand.nextInt(ReceiptType.values().length);
        ReceiptType receiptType = null;
        
        for(ReceiptType type : ReceiptType.values()){
            if(typeID == type.getValue()){
                receiptType = type;
                break;
            }
        }
        
        return receiptType;
        
    }
    
}
