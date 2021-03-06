package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.crossover.CrossoverHandler;
import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.mutator.MutatorService;
import com.facebook.genAlgo.solutionfinder.SolutionFinder;
import com.facebook.genAlgo.utils.RandomProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenePoolService {

    private final RandomProvider randomProvider;
    private final MutatorService mutatorService;
    private final Evaluator evaluator;
    private final CrossoverHandler crossoverHandler;
    private final SolutionFinder solutionFinder;

    public GenePoolService(RandomProvider randomProvider, MutatorService mutatorService,
                           Evaluator evaluator, CrossoverHandler crossoverHandler, SolutionFinder solutionFinder,
                           int size) {
        this.randomProvider = randomProvider;
        this.mutatorService = mutatorService;
        this.evaluator = evaluator;
        this.crossoverHandler = crossoverHandler;
        this.solutionFinder = solutionFinder;
    }

    public List<Gene> initializeGenes(int size) {
        if (size <= 0) {
            return Collections.emptyList();
        }
        List<Gene> listOfGenes = new ArrayList<>();
        while (size-- > 0) {
            listOfGenes.add(new Gene(randomProvider));
        }
        return listOfGenes;
    }

    public void makeMutation(List<Gene> poolOfGenes) {
        for (Gene gene : poolOfGenes) {
            mutatorService.mutate(gene);
        }
    }

    public void evaluateFitness(List<Gene> poolOfGenes) {
        for (Gene gene : poolOfGenes) {
            evaluator.setFitness(gene);
        }
    }

    public void makeCross(List<Gene> poolOfGenes) {
        crossoverHandler.performCross(poolOfGenes);
    }

}
