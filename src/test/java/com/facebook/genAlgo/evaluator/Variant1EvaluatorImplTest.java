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
evaluation formula 1 / (1+log10(1+delta))
 */
public class Variant1EvaluatorImplTest {

    Evaluator evaluator;
    Gene gene = mock(Gene.class);

    @DisplayName("Should setFitness calculate fitness for full match")
    @ParameterizedTest
    @MethodSource("setFitnessFullMatchArgumentsProvider")
    void setFitnessFullMatch(float fitnessExpected, char target, char evaluatedValue) {
        // given
        evaluator = new Variant1EvaluatorImpl(target);
        when(gene.getValues()).thenReturn(new char[]{evaluatedValue});

        // when
        float fitnessActual = evaluator.setFitness(gene);

        // then
        assertEquals(fitnessExpected, fitnessActual, 0.0001);
    }

    private static Stream<Arguments> setFitnessFullMatchArgumentsProvider() {
        return Stream.of(
                Arguments.of(1, (char) 100, (char) 100),
                Arguments.of(1, (char) 0, (char) 0),
                Arguments.of(1, 'ß', 'ß'),
                Arguments.of(1, ' ', ' '),
                Arguments.of(1, Character.MAX_VALUE, Character.MAX_VALUE)
        );
    }

    @DisplayName("Should setFitness calculate fitness for close match")
    @ParameterizedTest
    @MethodSource("setFitnessCloseMatchArgumentsProvider")
    void setFitnessCloseMatch(float fitnessExpected, char target, char evaluatedValue) {
        // given
        evaluator = new Variant1EvaluatorImpl(target);
        when(gene.getValues()).thenReturn(new char[]{evaluatedValue});

        // when
        float fitnessActual = evaluator.setFitness(gene);

        // then
        assertEquals(fitnessExpected, fitnessActual, 0.0001);
    }

    private static Stream<Arguments> setFitnessCloseMatchArgumentsProvider() {
        return Stream.of(
                Arguments.of(0.7686218, (char) 100, (char) 99),
                Arguments.of(0.6769925, (char) 100, (char) 98),
                Arguments.of(0.7686218, (char) 100, (char) 101),
                Arguments.of(0.7686218, (char) 0, (char) 1),
                Arguments.of(0.6769925, (char) 0, (char) 2),
                Arguments.of(0.62419635, (char) 0, (char) 3),
                Arguments.of(0.7686218, 'ß', 'ß' + 1),
                Arguments.of(0.7686218, 'ß', 'ß' - 1),
                Arguments.of(0.6769925, 'ß', 'ß' + 2),
                Arguments.of(0.6769925, 'ß', 'ß' - 2),
                Arguments.of(0.7686218, ' ', ' ' + 1),
                Arguments.of(0.7686218, ' ', ' ' - 1),
                Arguments.of(0.7686218, Character.MAX_VALUE, Character.MAX_VALUE - 1),
                Arguments.of(0.6769925, Character.MAX_VALUE, Character.MAX_VALUE - 2)
        );
    }
}
