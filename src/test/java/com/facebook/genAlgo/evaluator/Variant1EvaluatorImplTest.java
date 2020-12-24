package com.facebook.genAlgo.evaluator;

import com.facebook.genAlgo.gene.Gene;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
evaluation formula 1 / (1+log(1+delta))
 */
public class Variant1EvaluatorImplTest {

    Evaluator evaluator;
    Gene gene = mock(Gene.class);

    @DisplayName("Should setFitness calculate fitness for full match")
    @ParameterizedTest
    @MethodSource("setFitnessArgumentsProvider")
    void setFitness(float fitnessExpected, char target, char evaluatedValue) {
        // given
        evaluator = new Variant1EvaluatorImpl(target);
        when(gene.getValues()).thenReturn(new char[]{evaluatedValue});

        // when
        float fitnessActual = evaluator.setFitness(gene);

        // then
        assertEquals(fitnessExpected, fitnessActual, 0.0001);
    }

    private static Stream<Arguments> setFitnessArgumentsProvider() {
        return Stream.of(
                Arguments.of(1, (char) 100, (char) 100),
                Arguments.of(1, (char) 0, (char) 0),
                Arguments.of(1, 'ß', 'ß'),
                Arguments.of(1, ' ', ' '),
                Arguments.of(1, Character.MAX_VALUE, Character.MAX_VALUE)
        );
    }
}
