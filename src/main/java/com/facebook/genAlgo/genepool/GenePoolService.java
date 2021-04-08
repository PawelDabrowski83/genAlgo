package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.crossover.CrossoverHandler;
import com.facebook.genAlgo.crossover.CrossoverServiceFactory;
import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.evaluator.EvaluatorFactory;
import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.mutator.MutatorFactory;
import com.facebook.genAlgo.mutator.MutatorService;
import com.facebook.genAlgo.solutionfinder.SolutionFinder;
import com.facebook.genAlgo.utils.RandomProvider;
import com.facebook.genAlgo.utils.RandomProviderImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static com.facebook.genAlgo.mutator.MutatorConfig.MutatorEnum;
import static com.facebook.genAlgo.crossover.CrossoverServiceFactory.CrossoverEnum;
import static com.facebook.genAlgo.evaluator.EvaluatorFactory.EvaluatorEnum;

public class GenePoolService {

    private final RandomProvider randomProvider;
    private final MutatorService mutatorService;
    private final Evaluator evaluator;
    private final CrossoverHandler crossoverHandler;
    private final SolutionFinder solutionFinder;

    public GenePoolService(RandomProvider randomProvider, MutatorService mutatorService,
                           Evaluator evaluator, CrossoverHandler crossoverHandler, SolutionFinder solutionFinder) {
        this.randomProvider = randomProvider;
        this.mutatorService = mutatorService;
        this.evaluator = evaluator;
        this.crossoverHandler = crossoverHandler;
        this.solutionFinder = solutionFinder;
    }

    private GenePoolService(Builder builder) {
        this.randomProvider = builder.randomProvider;
        this.mutatorService = builder.mutatorService;
        this.evaluator = builder.evaluator;
        this.crossoverHandler = builder.crossoverHandler;
        this.solutionFinder = builder.solutionFinder;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private RandomProvider randomProvider = new RandomProviderImpl();
        private MutatorService mutatorService = new MutatorFactory().getMutator();
        private Evaluator evaluator;
        private CrossoverHandler crossoverHandler;
        private SolutionFinder solutionFinder;

        public Builder randomProvider(RandomProvider randomProvider) {
            this.randomProvider = randomProvider;
            return this;
        }

        public Builder mutatorService(MutatorEnum option) {
            this.mutatorService = new MutatorFactory().getMutator(option);
            return this;
        }

        public Builder evaluator(EvaluatorEnum evaluator) {
            return null;
        }

        public Builder crossoverHandler(CrossoverEnum option) {
            return null;
        }

        public Builder solutionFinder(SolutionFinder solutionFinder) {
            return null;
        }

        public GenePoolService build() {
            return new GenePoolService(this);
        }
    }

    public RandomProvider getRandomProvider() {
        return randomProvider;
    }

    public MutatorService getMutatorService() {
        return mutatorService;
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public CrossoverHandler getCrossoverHandler() {
        return crossoverHandler;
    }

    public SolutionFinder getSolutionFinder() {
        return solutionFinder;
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

    public boolean verifySolution(List<Gene> poolOfGenes) {
        return solutionFinder.findSolution(poolOfGenes);
    }

    public List<Gene> getSolution(List<Gene> poolOfGenes) {
        return solutionFinder.findSolvedGenes(poolOfGenes);
    }

    public void makeCross(List<Gene> poolOfGenes) {
        crossoverHandler.performCross(poolOfGenes);
    }

}
