package com.facebook.genAlgo.genepool;

public class GenePool {

    private GenePoolService genePoolService;
    private int generation;

    public GenePool(GenePoolService genePoolService) {
        this.genePoolService = genePoolService;
    }

    public void performEvolution() {
        genePoolService.makeCross();
        genePoolService.makeMutation();
        generation++;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
