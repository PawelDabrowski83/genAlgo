package com.facebook.genAlgo.evaluator;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.RandomProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/*
evaluation formula:
variant 2: (65535 - delta) / 65535
 */
public class MaxDeltaEvaluatorImplTest {

    Evaluator evaluator;
    RandomProvider randomProvider = mock(RandomProvider.class);
    Gene gene;

    @DisplayName("Should setFitness sets gene.fitness with full match")
    @ParameterizedTest
    @MethodSource("setFitnessFullMatchArgumentsProvider")
    void setFitnessFullMatch(float fitnessExpected, char target, char evaluatedValue) {
        // given
        evaluator = new MaxDeltaEvaluatorImpl(target);
        when(randomProvider.getInt(anyInt())).thenReturn((int) evaluatedValue);
        gene = new Gene(randomProvider);

        // when
        evaluator.setFitness(gene);

        // then
        assertEquals(fitnessExpected, gene.getFitness(), 0.00001);
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

    @DisplayName("Should setFitness sets gene.fitness with close match")
    @ParameterizedTest
    @MethodSource("setFitnessCloseMatchArgumentsProvider")
    void setFitnessCloseMatch(float fitnessExpected, char target, char evaluatedValue) {
        // given
        evaluator = new MaxDeltaEvaluatorImpl(target);
        when(randomProvider.getInt(anyInt())).thenReturn((int) evaluatedValue);
        gene = new Gene(randomProvider);

        // when
        evaluator.setFitness(gene);

        // then
        assertEquals(fitnessExpected, gene.getFitness(), 0.00001);
    }

    private static Stream<Arguments> setFitnessCloseMatchArgumentsProvider() {
        return Stream.of(
                Arguments.of(0.99998474f, (char) 100, (char) 99),
                Arguments.of(0.9999695f, (char) 100, (char) 98),
                Arguments.of(0.99998474f, (char) 100, (char) 101),

                Arguments.of(0.99998474f, (char) 0, (char) 1),
                Arguments.of(0.9999695f, (char) 0, (char) 2),
                Arguments.of(0.9999542f, (char) 0, (char) 3),

                Arguments.of(0.99998474f, 'ß', (char) ('ß' + 1)),
                Arguments.of(0.99998474f, 'ß', (char) ('ß' - 1)),
                Arguments.of(0.9999695f, 'ß', (char) ('ß' + 2)),
                Arguments.of(0.9999695f, 'ß', (char) ('ß' - 2)),

                Arguments.of(0.99998474f, ' ', (char) (' ' + 1)),
                Arguments.of(0.99998474f, ' ', (char) (' ' - 1)),

                Arguments.of(0.99998474f, Character.MAX_VALUE, (char) (Character.MAX_VALUE - 1)),
                Arguments.of(0.9999695f, Character.MAX_VALUE, (char) (Character.MAX_VALUE - 2))
        );
    }

    @DisplayName("Should setFitness sets gene.fitness with any match")
    @ParameterizedTest
    @MethodSource("setFitnessAnyMatchArgumentsProvider")
    void setFitnessAnyMatch(float fitnessExpected, char target, char evaluatedValue) {
        // given
        evaluator = new MaxDeltaEvaluatorImpl(target);
        when(randomProvider.getInt(anyInt())).thenReturn((int) evaluatedValue);
        gene = new Gene(randomProvider);

        // when
        evaluator.setFitness(gene);

        // then
        assertEquals(fitnessExpected, gene.getFitness(), 0.00001);
    }

    private static Stream<Arguments> setFitnessAnyMatchArgumentsProvider() {
        return Stream.of(
                Arguments.of(0.9984741f, (char) 100, Character.MIN_VALUE),
                Arguments.of(0.0015259022f, (char) 100, Character.MAX_VALUE),
                Arguments.of(0.843946f, (char) 100, '⡗'),
                Arguments.of(0.7218738f, (char) 100, '䞗'),
                Arguments.of(0.99923706f, (char) 100,  (char) 150),
                Arguments.of(0.99983215f, (char) 100, (char) 89),

                Arguments.of(0.9987793f, (char) 0, (char) 80),
                Arguments.of(0.99954224f, (char) 0, (char) 30),
                Arguments.of(0.94811934f, (char) 0, 'ൈ'),
                Arguments.of(0.869276f, (char) 0, 'ⅷ'),

                Arguments.of(0.0f, Character.MAX_VALUE, Character.MIN_VALUE),
                Arguments.of(0.92370486f, Character.MAX_VALUE, (char) (Character.MAX_VALUE - 5000)),
                Arguments.of(0.99980164f, Character.MAX_VALUE, (char) (Character.MAX_VALUE - 13)),
                Arguments.of(0.046707865f, Character.MAX_VALUE, '௵'),

                Arguments.of(0.98168916f, '௸', (char) ('௸' - 1200)),
                Arguments.of(0.9132067f, '௸', '∰'),
                Arguments.of(0.8998703f, '௸', '▚'),
                Arguments.of(0.95324636f, '௸', Character.MIN_VALUE),
                Arguments.of(0.046753645f, '௸', Character.MAX_VALUE)
        );
    }
}
