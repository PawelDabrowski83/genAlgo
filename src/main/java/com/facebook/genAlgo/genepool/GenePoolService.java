package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.crossover.CrossoverHandler;
import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.mutator.MutatorService;
import com.facebook.genAlgo.utils.RandomProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenePoolService {

    private final RandomProvider randomProvider;
    private final MutatorService mutatorService;
    private final Evaluator evaluator;
    private final CrossoverHandler crossoverHandler;
    private final List<Gene> poolOfGenes;

    public GenePoolService(RandomProvider randomProvider, MutatorService mutatorService,
                           Evaluator evaluator, CrossoverHandler crossoverHandler, int size) {
        this.randomProvider = randomProvider;
        this.mutatorService = mutatorService;
        this.evaluator = evaluator;
        this.crossoverHandler = crossoverHandler;
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
}
