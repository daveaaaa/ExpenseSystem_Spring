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
public class GAFactory {

    public static ArrayList<Individual> populateTrainingData(ArrayList<Receipt> receipts) {
        ArrayList<Individual> indivs = new ArrayList<>();

        for (Receipt receipt : receipts) {
            Individual individual = new Individual();

            individual.setNumberOfItems(receipt.getReceiptItems().validItemCount());
            individual.setTotal(receipt.getTotal());
            individual.setReceiptType(receipt.getType());
            individual.setChangable(false);
            indivs.add(individual);
        }

        return indivs;

    }

    public static ArrayList<Population> generateInitialPopulation(int size, int populationSize) {
        ArrayList<Population> populations = new ArrayList<>();
        Random rand = new Random(System.currentTimeMillis());

        for (int p = 0; p != size; p++) {
            Population population = new Population();
            for (int i = 0; i != populationSize; i++) {
                Individual individual = new Individual();

                int[] indiv = individual.getIndividual();
                
                int maxItems = rand.nextInt(GA.MIN_ITEMS);
                int maxTotal = rand.nextInt(GA.MAX_TOTAL);
                
                indiv[Individual.MIN_ITEMS] = rand.nextInt(maxItems);
                indiv[Individual.MAX_ITEMS] = maxItems;
                indiv[Individual.MIN_TOTAL] = rand.nextInt(maxTotal);
                indiv[Individual.MAX_TOTAL] = maxTotal;
                indiv[Individual.TYPE] = randomReceiptType(rand).getValue();
                
                individual.setChangable(true);
                individual.setIndividual(indiv);
            }

            populations.add(population);

        }

        return populations;
    }

    private static ReceiptType randomReceiptType(Random rand) {
        int typeID = rand.nextInt(ReceiptType.values().length);
        ReceiptType receiptType = null;

        for (ReceiptType type : ReceiptType.values()) {
            if (typeID == type.getValue()) {
                receiptType = type;
                break;
            }
        }

        return receiptType;

    }

}
