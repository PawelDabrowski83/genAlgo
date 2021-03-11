package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.gene.Gene;

import java.util.Collections;
import java.util.List;

public class GenePool {

    private final GenePoolService genePoolService;
    private int generation;
    private final List<Gene> poolOfGenes;

    public GenePool(GenePoolService genePoolService, int genePoolSize) {
        this.genePoolService = genePoolService;
        poolOfGenes = genePoolService.initializeGenes(genePoolSize);
    }

    public void performEvolution() {
        genePoolService.makeCross(poolOfGenes);
        genePoolService.makeMutation(poolOfGenes);
        generation++;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int solve() {
        while(!genePoolService.verifySolution(getPoolOfGenes())) {
            performEvolution();
        }
        return generation;
    }

    public List<Gene> getSolve() {
        return genePoolService.getSolution(getPoolOfGenes());
    }

    public List<Gene> getPoolOfGenes() {
        return poolOfGenes;
    }
}
