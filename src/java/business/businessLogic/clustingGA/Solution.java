/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.businessLogic.clustingGA;

import java.util.ArrayList;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author david
 */
public class Solution implements ServletContextListener {

    private ArrayList<Individual> solution;
    private boolean solutionSet;

    public ArrayList<Individual> getSolution() {
        return solution;
    }

    public void setSolution(ArrayList<Individual> solution) {
        this.solution = solution;
        solutionSet = true;
    }

    public boolean isSolutionSet() {
        return solutionSet;
    }
    
    
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
       solution = new ArrayList<>();
       solutionSet = false;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //TODO
    }
}
