package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.crossover.*;
import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.evaluator.EvaluatorFactory;
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
    @MethodSource
    public void shouldReturnInstanceOfGenePoolServiceWithSetCrossover(CrossoverServiceEnum crossoverEnum, Class<CrossoverService> instance) {
        // given

        // when
        GenePoolService genePoolService = GenePoolService.builder()
                .crossoverHandler(crossoverEnum)
                .build();

        // then
        assertThat(genePoolService.getCrossoverHandler()).isInstanceOf(instance);
    }

    public static Stream<Arguments> shouldReturnInstanceOfGenePoolServiceWithSetCrossover() {
        return Stream.of(
                Arguments.of(CrossoverServiceEnum.DEFAULT, BitPairCrossoverServiceImpl.class),
                Arguments.of(CrossoverServiceEnum.BIT_PAIR, BitPairCrossoverServiceImpl.class),
                Arguments.of(CrossoverServiceEnum.ODD_BITS, OddBitsCrossoverServiceImpl.class),
                Arguments.of(CrossoverServiceEnum.EVEN_BITS, EvenBitsCrossoverServiceImpl.class),
                Arguments.of(CrossoverServiceEnum.MIXING_HALVES, MixingHalvesCrossoverServiceImpl.class)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shouldReturnInstanceOfGenePoolServiceWithSetEvaluator(EvaluatorEnum evaluatorEnum, Class<Evaluator> instance) {
        // given

        // when
        GenePoolService genePoolService = GenePoolService.builder()
                .evaluator(evaluatorEnum)
                .build();

        // then
        assertThat(genePoolService.getCrossoverHandler()).isInstanceOf(instance);
    }

    public static Stream<Arguments> shouldReturnInstanceOfGenePoolServiceWithSetEvaluator() {
        return Stream.of(
                Arguments.of(EvaluatorEnum.DEFAULT, MaxDeltaEvaluatorImpl.class),
                Arguments.of(EvaluatorEnum.MAX_DELTA, MaxDeltaEvaluatorImpl.class),
                Arguments.of(EvaluatorEnum.LOG, LogarithmicEvaluatorImpl.class)
        );
    }
}