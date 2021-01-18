package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.RandomProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class MultipleMutatorTest {

    RandomProvider randomProvider = mock(RandomProvider.class);
    MutatorService mutatorService;

    @DisplayName("Should mutate change Gene.value when mutation is guaranteed.")
    @ParameterizedTest
    @MethodSource("mutateGuaranteedArgumentsProvider")
    void mutateGuaranteed(int geneValue) {
        // given
        mutatorService = new MultipleMutator(randomProvider, 1);
        Gene gene = new Gene(randomProvider);
        gene.setValue((char) geneValue);
        int initialGeneValue = gene.getValue();
        when(randomProvider.getInt(anyInt())).thenReturn(1);

        // when
        mutatorService.mutate(gene);
        int actualGeneValue = gene.getValue();

        // then
        assertNotEquals(initialGeneValue, actualGeneValue);
    }

    private static Stream<Arguments> mutateGuaranteedArgumentsProvider() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(100),
                Arguments.of(10000),
                Arguments.of(Character.MAX_VALUE)
        );
    }

    @DisplayName("Should mutate work properly when change occurs")
    @ParameterizedTest
    @MethodSource("mutateArgumentsProvider")
    void mutate(char geneValue, char expectedGeneValue, int mutationTimes, int mutationStep, float mutationChance, float mutationScore) {
        // given
        Gene gene = new Gene(randomProvider);
        gene.setValue(geneValue);
        MutatorService mutator = new MultipleMutator(randomProvider, mutationChance);
        doAnswer(new Answer<Integer>() {
            private int count = 0;
            public Integer answer(InvocationOnMock invocation) {
                count++;
                if (count == 1) {
                    return mutationTimes;
                }
                return mutationStep + count - 2;
            }
        }).when(randomProvider).getInt(anyInt());
        when(randomProvider.getFloat()).thenReturn(mutationScore);

        // when
        mutator.mutate(gene);
        char actualGeneValue = gene.getValue();

        // then
        assertEquals(expectedGeneValue, actualGeneValue);
    }

    private static Stream<Arguments> mutateArgumentsProvider() {
        return Stream.of(
                Arguments.of(Character.MIN_VALUE, (char) 1, 1, 0, 1, 0.5f),
                Arguments.of((char) 0b1100_0111, (char) 0b1100_0001, 2, 1, 0.25f, 0.24f),
                Arguments.of((char) 0b1100_0111, (char) 0b1111_1111, 3, 3, 0.25f, 0.24f),
                Arguments.of((char) 0b1100_0111, (char) 0b1111_1100_0111, 4, 8, 0.25f, 0.24f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0010_1110, 5, 0, 0.76f, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_1100_1101, 6, 2, 0.76f, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1010_1100_0001, 7, 4, 0.76f, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b1100_0011_0011_0001, 7, 9, 0.76f, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0010_1101_0011_0001, 1, 12, 0.76f, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1010_1100_1111, 10, 1, 0.76f, 0.75f)
        );
    }

    @DisplayName("Should mutate work properly when mutationChance is not reached")
    @ParameterizedTest
    @MethodSource("mutateNoChanceArgumentsProvider")
    void mutateNoChance(char geneValue, char expectedGeneValue, int mutationTimes, int mutationStep, float mutationChance, float mutationScore) {
        // given
        Gene gene = new Gene(randomProvider);
        gene.setValue(geneValue);
        MutatorService mutator = new MultipleMutator(randomProvider, mutationChance);
        when(randomProvider.getInt(anyInt())).thenReturn(mutationTimes);
        when(randomProvider.getFloat()).thenReturn(mutationScore);

        // when
        mutator.mutate(gene);
        char actualGeneValue = gene.getValue();

        // then
        assertEquals(expectedGeneValue, actualGeneValue);
    }

    private static Stream<Arguments> mutateNoChanceArgumentsProvider() {
        return Stream.of(
                Arguments.of(Character.MIN_VALUE, (char) 0, 1, 0, 0.5f, 0.99f),
                Arguments.of((char) 0b1100_0111, (char) 0b1100_0111, 2, 1, 0.25f, 0.26f),
                Arguments.of((char) 0b1100_0111, (char) 0b1100_0111, 4, 3, 0.25f, 0.27f),
                Arguments.of((char) 0b1100_0111, (char) 0b1100_0111, 1, 8, 0.25f, 0.28f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 8, 0, 0.74f, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 1, 2, 0.72f, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 0, 4, 0.76f, 0.77f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 4, 9, 0.76f, 0.78f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 1, 12, 0.73f, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 9, 15, 0, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 0, 0, 0.74f, 0.73f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 2, 0, 0.73f, 0.73f)
        );
    }
}
