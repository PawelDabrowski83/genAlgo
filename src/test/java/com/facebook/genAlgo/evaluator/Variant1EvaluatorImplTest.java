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
        when(gene.getValue()).thenReturn(evaluatedValue);

        // when
        float fitnessActual = evaluator.calculateFitness(gene);
        evaluator.setFitness(gene);

        // then
        assertEquals(fitnessExpected, gene.fitness, 0.0001);
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
        when(gene.getValue()).thenReturn(evaluatedValue);

        // when
        evaluator.setFitness(gene);

        // then
        assertEquals(fitnessExpected, gene.fitness, 0.0001);
    }

    private static Stream<Arguments> setFitnessCloseMatchArgumentsProvider() {
        return Stream.of(
                Arguments.of(0.7686218f, (char) 100, (char) 99),
                Arguments.of(0.6769925f, (char) 100, (char) 98),
                Arguments.of(0.7686218f, (char) 100, (char) 101),

                Arguments.of(0.7686218f, (char) 0, (char) 1),
                Arguments.of(0.6769925f, (char) 0, (char) 2),
                Arguments.of(0.62419635f, (char) 0, (char) 3),

                Arguments.of(0.7686218f, 'ß', (char) ('ß' + 1)),
                Arguments.of(0.7686218f, 'ß', (char) ('ß' - 1)),
                Arguments.of(0.6769925f, 'ß', (char) ('ß' + 2)),
                Arguments.of(0.6769925f, 'ß', (char) ('ß' - 2)),

                Arguments.of(0.7686218f, ' ', (char) (' ' + 1)),
                Arguments.of(0.7686218f, ' ', (char) (' ' - 1)),

                Arguments.of(0.7686218f, Character.MAX_VALUE, (char) (Character.MAX_VALUE - 1)),
                Arguments.of(0.6769925f, Character.MAX_VALUE, (char) (Character.MAX_VALUE - 2))
        );
    }

    @DisplayName("Should setFitness calculate fitness for any match")
    @ParameterizedTest
    @MethodSource("setFitnessAnyMatchArgumentsProvider")
    void setFitnessAnyMatch(float fitnessExpected, char target, char evaluatedValue) {
        // given
        evaluator = new Variant1EvaluatorImpl(target);
        when(gene.getValue()).thenReturn(evaluatedValue);

        // when
        evaluator.setFitness(gene);

        // then
        assertEquals(fitnessExpected, gene.fitness, 0.0001);
    }

    private static Stream<Arguments> setFitnessAnyMatchArgumentsProvider() {
        return Stream.of(
                Arguments.of(0.33285388f, (char) 100, Character.MIN_VALUE),
                Arguments.of(0.1719449f, (char) 100, Character.MAX_VALUE),
                Arguments.of(0.19960913f, (char) 100, '⡗'),
                Arguments.of(0.19008736f, (char) 100, '䞗'),
                Arguments.of(0.36933485f, (char) 100,  (char) 150),
                Arguments.of(0.48095855f, (char) 100, (char) 89),

                Arguments.of(0.34382161f, (char) 0, (char) 80),
                Arguments.of(0.40138692f, (char) 0, (char) 30),
                Arguments.of(0.2206723f, (char) 0, 'ൈ'),
                Arguments.of(0.20272136f, (char) 0, 'ⅷ'),

                Arguments.of(0.17192529f, Character.MAX_VALUE, Character.MIN_VALUE),
                Arguments.of(0.21280867f, Character.MAX_VALUE, (char) (Character.MAX_VALUE - 5000)),
                Arguments.of(0.46595544f, Character.MAX_VALUE, (char) (Character.MAX_VALUE - 13)),
                Arguments.of(0.17254153f, Character.MAX_VALUE, '௵'),

                Arguments.of(0.2451255f, '௸', (char) ('௸' - 1200)),
                Arguments.of(0.21030335f, '௸', '∰'),
                Arguments.of(0.20759368f, '௸', '▚'),
                Arguments.of(0.22289436f, '௸', Character.MIN_VALUE),
                Arguments.of(0.17254214f, '௸', Character.MAX_VALUE)
        );
    }
}
