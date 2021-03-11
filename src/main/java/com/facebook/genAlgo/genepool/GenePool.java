package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.gene.Gene;

import java.util.List;

public class GenePool {

    private GenePoolService genePoolService;
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
        return 0;
    }

    public List<Gene> getPoolOfGenes() {
        return poolOfGenes;
    }
}
