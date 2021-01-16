package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.RandomProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

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
}
