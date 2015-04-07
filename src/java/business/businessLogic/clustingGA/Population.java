/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.clustingGA;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Population {

    private ArrayList<Individual> population;

    public Population() {
        population = new ArrayList<>();
    }

    void addIndividual(Individual individual) {
        population.add(individual);
    }
}
