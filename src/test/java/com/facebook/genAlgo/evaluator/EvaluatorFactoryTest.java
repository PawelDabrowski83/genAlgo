package com.facebook.genAlgo.evaluator;

import com.facebook.genAlgo.mutator.MutatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static com.facebook.genAlgo.evaluator.EvaluatorFactory.EvaluatorEnum;

public class EvaluatorFactoryTest {

    char target;
    EvaluatorFactory evaluatorFactory;

    @BeforeEach
    public void init() {
        target = 'Z';
        evaluatorFactory = new EvaluatorFactory();
    }

    @Test
    public void shouldReturnMaxDeltaEvaluatorWhenGetEvaluatorWithoutArgsIsEvoked() {
        // when
        Evaluator evaluator = evaluatorFactory.getEvaluator(target);

        // then
        assertThat(evaluator).isInstanceOf(MaxDeltaEvaluatorImpl.class);
    }

    @DisplayName("Should EvaluatorFactory return proper instance of Evaluator given enum argument")
    @ParameterizedTest
    @MethodSource("evaluatorFactoryTestArgumentsProvider")
    void shouldEvaluatorFactoryReturnProperInstanceOfEvaluator(Class<Evaluator> expected, EvaluatorEnum option) {
        // when
        Evaluator actualEvaluator = evaluatorFactory.getEvaluator(target, option);

        // then
        assertThat(actualEvaluator).isInstanceOf(expected);
    }

    private static Stream<Arguments> evaluatorFactoryTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(MaxDeltaEvaluatorImpl.class, EvaluatorEnum.DEFAULT),
                Arguments.of(MaxDeltaEvaluatorImpl.class, EvaluatorEnum.MAX_DELTA),
                Arguments.of(LogarithmicEvaluatorImpl.class, EvaluatorEnum.LOG)
        );
    }

}
