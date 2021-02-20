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

    private final RandomProvider randomProvider;
    private final MutatorService mutatorService;
    private final Evaluator evaluator;
    private final CrossoverHandler crossoverHandler;
    private final List<Gene> poolOfGenes;
    private int generation;

    public GenePool(RandomProvider randomProvider, MutatorService mutatorService, Evaluator evaluator,
                    CrossoverHandler crossoverHandler, int size) {
        this.randomProvider = randomProvider;
        this.mutatorService = mutatorService;
        this.evaluator = evaluator;
        this.crossoverHandler = crossoverHandler;
        this.poolOfGenes = initializeGenes(size);
    }


    public void performEvolution() {
        generation++;
    }

    public List<Gene> getPoolOfGenes() {
        return poolOfGenes;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
