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
    
    
    public GA(){
    }

    public int generateModel(){
        getTrainingData();
        generatePopulationSet();
        int fitness = doGA();
        
        return fitness;
    }
    
    private void getTrainingData(){
        ArrayList<Receipt> receipts = new ArrayList<>();
        try{
            DBHandler handler = MongoDBHelper.getDBHandler();
            receipts = handler.findReceipt();
        } catch (Exception ex){
            //TODO: logging
        }
        trainingData = IndividualFactory.populateTrainingData(receipts);
    }
    
    private void generatePopulationSet(){
        population = IndividualFactory.generateInitialPopulation(MAX_POPULATION,MAX_POPULATION_SIZE,MAX_ITEMS, MAX_TOTAL);
    }
    
    private int doGA(){
        int fitness = 0;
        
        
        return fitness;
    }
}
