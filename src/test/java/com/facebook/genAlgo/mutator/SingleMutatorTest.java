package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.RandomProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SingleMutatorTest {

    RandomProvider randomProvider = mock(RandomProvider.class);
    MutatorService mutatorService;

    @DisplayName("Should mutate change Gene.value when mutation should be guaranteed.")
    @ParameterizedTest
    @MethodSource("mutateGuaranteedArgumentsProvider")
    void mutateGuaranteed(int geneValue) {
        // given
        mutatorService = new SingleMutator(randomProvider, 1);
        Gene gene = new Gene(randomProvider);
        gene.setValue((char) geneValue);
        int initialGeneValue = gene.getValue();

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
    void mutate(char geneValue, char expectedGeneValue, int mutationStep, float mutationChance, float mutationScore) {
        // given
        Gene gene = new Gene(randomProvider);
        gene.setValue(geneValue);
        MutatorService mutator = new SingleMutator(randomProvider, mutationChance);
        when(randomProvider.getInt(anyInt())).thenReturn(mutationStep);
        when(randomProvider.getFloat()).thenReturn(mutationScore);

        // when
        mutator.mutate(gene);
        char actualGeneValue = gene.getValue();

        // then
        assertEquals(expectedGeneValue, actualGeneValue);
    }

    private static Stream<Arguments> mutateArgumentsProvider() {
        return Stream.of(
                Arguments.of(Character.MIN_VALUE, (char) 1, 0, 1, 0.5),
                Arguments.of((char) 0b1100_0111, (char) 0b1100_0101, 1, 0.25, 0.24),
                Arguments.of((char) 0b1100_0111, (char) 0b1100_1111, 3, 0.25, 0.24),
                Arguments.of((char) 0b1100_0111, (char) 0b1_1100_0111, 8, 0.25, 0.24),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0000, 0, 0.76, 0,75),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0101, 2, 0.76, 0,75),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0010_0001, 4, 0.76, 0,75),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1111_0011_0001, 9, 0.76, 0,75),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0010_1101_0011_0001, 12, 0.76, 0,75),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b1011_1101_0011_0001, 15, 0.76, 0,75)
        );
    }

}
