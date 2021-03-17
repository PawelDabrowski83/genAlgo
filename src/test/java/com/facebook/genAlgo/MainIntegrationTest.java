package com.facebook.genAlgo;

import com.facebook.genAlgo.crossover.BitPairCrossoverServiceImpl;
import com.facebook.genAlgo.crossover.CrossoverHandler;
import com.facebook.genAlgo.crossover.CrossoverService;
import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.evaluator.LogarithmicEvaluatorImpl;
import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.genepool.GenePool;
import com.facebook.genAlgo.genepool.GenePoolService;
import com.facebook.genAlgo.mutator.MutatorService;
import com.facebook.genAlgo.mutator.SingleMutator;
import com.facebook.genAlgo.solutionfinder.SolutionFinder;
import com.facebook.genAlgo.utils.RandomProvider;
import com.facebook.genAlgo.utils.RandomProviderImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MainIntegrationTest {

    char target = 'Z';
    float mutationChance = 0.01f;
    int genePoolSize = 1000;
    RandomProvider randomProvider = new RandomProviderImpl();
    Evaluator evaluator = new LogarithmicEvaluatorImpl(target);
    MutatorService mutator = new SingleMutator(randomProvider, mutationChance);
    CrossoverService crossoverService = new BitPairCrossoverServiceImpl();
    CrossoverHandler crossoverHandler = new CrossoverHandler(crossoverService);
    SolutionFinder solutionFinder = new SolutionFinder(target);

    @DisplayName("Should return generation number bigger than 0 when solve() is evoked")
    @Test
    public void shouldNumberOfGenerationsBeGreaterThanZeroWhenSolveIsEvoked() {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutator, evaluator, crossoverHandler, solutionFinder);
        GenePool genePool = new GenePool(genePoolService, genePoolSize);

        // when
        int numberOfGenerationsToSolve = genePool.solve();

        // then
        assertThat(numberOfGenerationsToSolve).isGreaterThan(0);
    }

    @DisplayName("Should return not empty list of Genes when getSolve() is evoked")
    @Test
    public void shouldReturnListOfGeneWhenGetSolveIsEvoked() {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutator, evaluator, crossoverHandler, solutionFinder);
        GenePool genePool = new GenePool(genePoolService, genePoolSize);

        // when
        List<Gene> solvedGenes = genePool.getSolve();

        // then
        assertThat(solvedGenes).isNotEmpty();
    }
}
