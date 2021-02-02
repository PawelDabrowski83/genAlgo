package com.facebook.genAlgo.genepool;

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
    private final List<Gene> poolOfGenes;
    private int generation;

    public GenePool(RandomProvider randomProvider, MutatorService mutatorService, Evaluator evaluator, int size) {
        this.randomProvider = randomProvider;
        this.mutatorService = mutatorService;
        this.evaluator = evaluator;
        this.poolOfGenes = initializeGenes(size);
    }

    private List<Gene> initializeGenes(int size) {
        if (size <= 0) {
            return Collections.emptyList();
        }
        List<Gene> listOfGenes = new ArrayList<>();
        while (size-- > 0) {
            listOfGenes.add(new Gene(randomProvider));
        }
        return listOfGenes;
    }

    public void makeMutation() {
        for (Gene gene : poolOfGenes) {
            mutatorService.mutate(gene);
        }
    }

    public void evaluateFitness() {
        for (Gene gene : poolOfGenes) {
            evaluator.setFitness(gene);
        }
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
