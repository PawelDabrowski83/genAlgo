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

    public GenePool(RandomProvider randomProvider, MutatorService mutatorService, Evaluator evaluator, int size) {
        this.randomProvider = randomProvider;
        this.mutatorService = mutatorService;
        this.evaluator = evaluator;
        initializeGenes(size);
    }

    private List<Gene> initializeGenes(int size) {
        return null;
    }

    public void makeMutation() {
    }

    public void evaluateFitness() {
    }

    public void performEvaluation() {
    }

    public List<Gene> getPoolOfGenes() {
        return poolOfGenes;
    }

    public void setPoolOfGenes(List<Gene> poolOfGenes) {
        this.poolOfGenes = poolOfGenes;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
