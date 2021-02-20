package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.crossover.CrossoverHandler;
import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.mutator.MutatorService;
import com.facebook.genAlgo.utils.RandomProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenePool {

    private GenePoolService genePoolService;
    private int generation;

    public GenePool(GenePoolService genePoolService) {
        this.genePoolService = genePoolService;
    }

    public void performEvolution() {
        generation++;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
