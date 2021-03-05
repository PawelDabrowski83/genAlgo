package com.facebook.genAlgo.solutionfinder;

import com.facebook.genAlgo.gene.Gene;

import java.util.List;

public class SolutionFinder {
    
    private final char target;

    public SolutionFinder(char target) {
        this.target = target;
    }

    public Boolean findSolution(List<Gene> geneList) {
     for (Gene gene : geneList) {
         if (gene.getFitness() == 1) {
             return true;
         }
     }
     return false;
    }
}
