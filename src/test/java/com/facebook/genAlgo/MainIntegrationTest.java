package com.facebook.genAlgo;

import com.facebook.genAlgo.crossover.BitPairCrossoverServiceImpl;
import com.facebook.genAlgo.crossover.CrossoverHandler;
import com.facebook.genAlgo.crossover.CrossoverService;
import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.evaluator.LogarithmicEvaluatorImpl;
import com.facebook.genAlgo.genepool.GenePool;
import com.facebook.genAlgo.genepool.GenePoolService;
import com.facebook.genAlgo.mutator.MutatorService;
import com.facebook.genAlgo.mutator.SingleMutator;
import com.facebook.genAlgo.utils.RandomProvider;
import com.facebook.genAlgo.utils.RandomProviderImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MainIntegrationTest {

    @Test
    public void shouldPerformSomeEvolutionOnSolve() {
        // given
        char target = 'Z';
        float mutationChance = 0.01f;
        int genePoolSize = 1000;
        RandomProvider randomProvider = new RandomProviderImpl();
        Evaluator evaluator = new LogarithmicEvaluatorImpl(target);
        MutatorService mutator = new SingleMutator(randomProvider, mutationChance);
        CrossoverService crossoverService = new BitPairCrossoverServiceImpl();
        CrossoverHandler crossoverHandler = new CrossoverHandler(crossoverService);

        GenePoolService genePoolService = new GenePoolService(randomProvider, mutator, evaluator, crossoverHandler, genePoolSize);
        GenePool genePool = new GenePool(genePoolService);

        // when
        int numberOfGenerationsToSolve = genePool.solve();

        // then
        assertThat(numberOfGenerationsToSolve).isGreaterThan(0);
    }
}
