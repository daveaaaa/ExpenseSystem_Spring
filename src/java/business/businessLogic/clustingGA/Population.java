/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.clustingGA;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author david
 */
public class Population {

    private ArrayList<Individual> population;

    public Population(ArrayList<Individual> population){
        this.population = population; 
    }
    
    public Population() {
        population = new ArrayList<>();
    }

    void addIndividual(Individual individual) {
        population.add(individual);
    }

    int getSize() {
        return population.size();
    }

    public ArrayList<Individual> crossover(int crossoverPoint, Population parent2) {
        ArrayList<Individual> child = new ArrayList<>();
        ArrayList<Individual> population2 = parent2.getPopulation();
        
        for(int i = 0; i != crossoverPoint; i++){
            child.add(population.get(i));
        }
        
        for(int i = crossoverPoint; i != population.size(); i++){
            child.add(population2.get(i));
        }
        
        return child;

    }

    public ArrayList<Individual> getPopulation() {
        return population;
    }

    public void mutate(int mutantPosition, Random rand) {
        Individual mutant = population.get(mutantPosition);
    
        int position = rand.nextInt(mutant.getSize());
        
        mutant.mutate(position); 
    }
}
