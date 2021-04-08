package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.crossover.*;
import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.evaluator.EvaluatorFactory.EvaluatorEnum;
import com.facebook.genAlgo.evaluator.LogarithmicEvaluatorImpl;
import com.facebook.genAlgo.evaluator.MaxDeltaEvaluatorImpl;
import com.facebook.genAlgo.mutator.SingleMutator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.facebook.genAlgo.crossover.CrossoverServiceFactory.*;
import static org.assertj.core.api.Assertions.*;

class GenePoolServiceBuilderTest {

    @Test
    public void shouldReturnInstanceOfGenePoolServiceWithDefaultParameters() {
        // given

        // when
        GenePoolService genePoolService = GenePoolService.builder()
                .build();

        // then
        assertThat(genePoolService.getMutatorService()).isInstanceOf(SingleMutator.class);
        assertThat(genePoolService.getEvaluator()).isInstanceOf(MaxDeltaEvaluatorImpl.class);
        assertThat(genePoolService.getCrossoverHandler()).isInstanceOf(BitPairCrossoverServiceImpl.class);
    }

    @ParameterizedTest
    @MethodSource("shouldReturnInstanceOfGenePoolServiceWithSetCrossover")
    public void shouldReturnInstanceOfGenePoolServiceWithSetCrossover(CrossoverEnum crossoverEnum, Class<CrossoverService> instance) {
        // given

        // when
        GenePoolService genePoolService = GenePoolService.builder()
                .crossoverHandler(crossoverEnum)
                .build();

        // then
        assertThat(genePoolService.getCrossoverHandler()).isInstanceOf(instance);
    }

    private static Stream<Arguments> shouldReturnInstanceOfGenePoolServiceWithSetCrossover() {
        return Stream.of(
                Arguments.of(CrossoverEnum.DEFAULT, BitPairCrossoverServiceImpl.class),
                Arguments.of(CrossoverEnum.BIT_PAIR, BitPairCrossoverServiceImpl.class),
                Arguments.of(CrossoverEnum.ODD_BITS, OddBitsCrossoverServiceImpl.class),
                Arguments.of(CrossoverEnum.EVEN_BITS, EvenBitsCrossoverServiceImpl.class),
                Arguments.of(CrossoverEnum.MIXING_HALVES, MixingHalvesCrossoverServiceImpl.class)
        );
    }

    @ParameterizedTest
    @MethodSource("shouldReturnInstanceOfGenePoolServiceWithSetEvaluator")
    public void shouldReturnInstanceOfGenePoolServiceWithSetEvaluator(EvaluatorEnum evaluatorEnum, Class<Evaluator> instance) {
        // given

        // when
        GenePoolService genePoolService = GenePoolService.builder()
                .evaluator(evaluatorEnum)
                .build();

        // then
        assertThat(genePoolService.getCrossoverHandler()).isInstanceOf(instance);
    }

    private static Stream<Arguments> shouldReturnInstanceOfGenePoolServiceWithSetEvaluator() {
        return Stream.of(
                Arguments.of(EvaluatorEnum.DEFAULT, MaxDeltaEvaluatorImpl.class),
                Arguments.of(EvaluatorEnum.MAX_DELTA, MaxDeltaEvaluatorImpl.class),
                Arguments.of(EvaluatorEnum.LOG, LogarithmicEvaluatorImpl.class)
        );
    }
}