package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.mutator.MutatorService;
import com.facebook.genAlgo.utils.RandomProvider;

import java.util.Collections;
import java.util.List;

public class GenePool {

    private RandomProvider randomProvider;
    private MutatorService mutatorService;
    private Evaluator evaluator;
    private List<Gene> poolOfGenes;
    private int generation;

    public GenePool(RandomProvider randomProvider, MutatorService mutatorService, Evaluator evaluator, int generation) {
        this.randomProvider = randomProvider;
        this.mutatorService = mutatorService;
        this.evaluator = evaluator;
        this.generation = generation;
    }

    public List<Gene> initializeGenes(int size) {
        return null;
    }

    public void makeMutation() {
    }

    public void evaluateFitness() {
    }

    public void performEvaluation() {
    }
}
