package com.facebook.genAlgo.solutionfinder;

import com.facebook.genAlgo.gene.Gene;

import java.util.List;
import java.util.stream.Collectors;

public class SolutionFinder {

    private final char target;

    public SolutionFinder(char target) {
        this.target = target;
    }

    public Boolean findSolution(List<Gene> geneList) {
        for (Gene gene : geneList) {
            if (gene.getValue() == target) {
                return true;
            }
        }
        return false;
    }

    public List<Gene> findSolvedGenes(List<Gene> geneList) {
        return geneList.stream()
                .filter(gene -> gene.getValue() == target)
                .collect(Collectors.toList());
    }
}
