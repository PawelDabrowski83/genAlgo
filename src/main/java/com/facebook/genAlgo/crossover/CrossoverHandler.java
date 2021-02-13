package com.facebook.genAlgo.crossover;

import com.facebook.genAlgo.gene.Gene;

import java.util.Comparator;
import java.util.List;

public class CrossoverHandler {

    private final CrossoverService crossoverService;

    public CrossoverHandler(CrossoverService crossoverService) {
        this.crossoverService = crossoverService;
    }

    public void performCross(List<Gene> geneList) {
        fitnessSort(geneList);
        for (int i = 0; i < geneList.size(); i = i = i + 2) {
            crossoverService.cross(geneList.get(i), geneList.get(i + 1));
        }
    }


    private void fitnessSort(List<Gene> geneList) {
        geneList.sort(Comparator.comparing((x) -> x.getFitness()));
    }
}
