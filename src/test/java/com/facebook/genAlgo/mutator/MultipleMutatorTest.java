package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.RandomProvider;
import com.facebook.genAlgo.utils.RandomProviderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class MultipleMutatorTest {

    RandomProvider randomProvider = mock(RandomProviderImpl.class);
    MutatorService mutatorService;
    Gene gene;

    @BeforeEach
    public void init() {
        gene = new Gene(randomProvider);
    }

    @DisplayName("Should mutate change Gene.value when mutation is guaranteed.")
    @ParameterizedTest
    @ValueSource(ints = {
        Character.MIN_VALUE, Character.MAX_VALUE, 0b1101_0010, 11, 100, 121, 1001, 10000, 56789, Character.MAX_VALUE - 1
    })
    void mutateGuaranteed(int initialGeneValue) {
        // given
        mutatorService = new MultipleMutator(randomProvider, 1);
        gene.setValue((char) initialGeneValue);
        when(randomProvider.getIntFromRange(anyInt(), anyInt())).thenCallRealMethod();

        // when
        mutatorService.mutate(gene);
        int actualGeneValue = gene.getValue();

        // then
        assertNotEquals(initialGeneValue, actualGeneValue);
    }

    @DisplayName("Should mutate work properly when change occurs")
    @ParameterizedTest
    @MethodSource("mutateArgumentsProvider")
    void mutate(char geneValue, char expectedGeneValue, int mutationTimes, int mutationStep, float mutationChance, float mutationScore) {
        // given
        gene.setValue(geneValue);
        mutatorService = new MultipleMutator(randomProvider, mutationChance);
        doAnswer(new Answer<Integer>() {
            private int count = 0;
            public Integer answer(InvocationOnMock invocation) {
//                count++;
                return mutationStep + count++;
            }
        }).when(randomProvider).getInt(anyInt());
        when(randomProvider.getIntFromRange(anyInt(), anyInt())).thenReturn(mutationTimes);
        when(randomProvider.getFloat()).thenReturn(mutationScore);

        // when
        mutatorService.mutate(gene);
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
    void mutateNoChance(char geneValue, char expectedGeneValue, int mutationTimes, float mutationChance, float mutationScore) {
        // given
        gene.setValue(geneValue);
        mutatorService = new MultipleMutator(randomProvider, mutationChance);
        when(randomProvider.getInt(anyInt())).thenReturn(mutationTimes);
        when(randomProvider.getFloat()).thenReturn(mutationScore);

        // when
        mutatorService.mutate(gene);
        char actualGeneValue = gene.getValue();

        // then
        assertEquals(expectedGeneValue, actualGeneValue);
    }

    private static Stream<Arguments> mutateNoChanceArgumentsProvider() {
        return Stream.of(
                Arguments.of(Character.MIN_VALUE, (char) 0, 1, 0.5f, 0.99f),
                Arguments.of((char) 0b1100_0111, (char) 0b1100_0111, 2, 0.25f, 0.26f),
                Arguments.of((char) 0b1100_0111, (char) 0b1100_0111, 4, 0.25f, 0.27f),
                Arguments.of((char) 0b1100_0111, (char) 0b1100_0111, 1, 0.25f, 0.28f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 8, 0.74f, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 1, 0.72f, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 0, 0.76f, 0.77f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 4, 0.76f, 0.78f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 1, 0.73f, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 9, 0, 0.75f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 0, 0.74f, 0.73f),
                Arguments.of((char) 0b0011_1101_0011_0001, (char) 0b0011_1101_0011_0001, 2, 0.73f, 0.73f)
        );
    }
}
