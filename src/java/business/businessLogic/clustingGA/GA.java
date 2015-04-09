/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.clustingGA;

import business.businessModel.Receipt;
import databaseAccess.DBHandler;
import databaseAccess.mongoDB.MongoDBHelper;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author david
 */
public class GA {

    public final static int MIN_ITEMS = 1;
    public final static int MAX_ITEMS = 1000;
    public final static int MIN_TOTAL = 1;      //£ 0.01
    public final static int MAX_TOTAL = 500000; //£ 5000.00
    public final static int MAX_POPULATION = 1000;
    public final static int MAX_POPULATION_SIZE = 3;
    public final static int MAX_GENERATIONS = 10000;
    public final static double MUTATION_RATE = 0.01;  //1%
    public final static double CROSSOVER_RATE = 0.60; //60%

    private ArrayList<Individual> trainingData;
    private ArrayList<Population> population;

    public GA() {
    }

    public int generateModel(DBHandler handler) {
        getTrainingData(handler);
        generatePopulationSet();
        int fitness = doGA();

        return fitness;
    }

    private void getTrainingData(DBHandler handler) {
        ArrayList<Receipt> receipts = new ArrayList<>();
        try {
            receipts = handler.listAllReceipts();
        } catch (Exception ex) {
            //TODO: logging
        }
        trainingData = GAFactory.populateTrainingData(receipts);
    }

    private void generatePopulationSet() {
        population = GAFactory.generateInitialPopulation(MAX_POPULATION, MAX_POPULATION_SIZE);
    }

    private int doGA() {
        Random rand = new Random(System.currentTimeMillis());
        int fitness = 0;

        for (int i = 0; i != MAX_GENERATIONS; i++) {
            population = doSelection(rand);
            population = doCrossOver(rand);
            population = doMutation(rand);
        }
        return fitness;
    }

    private ArrayList<Population> doSelection(Random rand) {
        ArrayList<Population> nextGeneration = new ArrayList<>();
        for (int i = 0; i != MAX_POPULATION; i++) {

        }
        return nextGeneration;
    }

    private ArrayList<Population> doCrossOver(Random rand) {

        ArrayList<Population> nextGeneration = new ArrayList<>();

        for (Population parent1 : population) {
            if (rand.nextDouble() > CROSSOVER_RATE) {
                int parent2ID = rand.nextInt(population.size());
                Population parent2 = population.get(parent2ID);

                int crossoverPoint = rand.nextInt(parent1.getSize());

                Population child = new Population(parent1.crossover(crossoverPoint, parent2));
                nextGeneration.add(child);
            } else {
                nextGeneration.add(parent1);
            }
        }
        return nextGeneration;
    }

    private ArrayList<Population> doMutation(Random rand) {
        ArrayList<Population> nextGeneration = new ArrayList<>();
        for (Population mutant : population) {
            if (MUTATION_RATE > rand.nextDouble()) {
                int mutantPosition = rand.nextInt(mutant.getSize());
                mutant.mutate(mutantPosition, rand); 
            }
        }
        return nextGeneration;
    }

}
