package com.facebook.genAlgo.crossover;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.RandomProvider;
import com.facebook.genAlgo.utils.RandomProviderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class EvenBitesCrossoverServiceImplTest {

    @ParameterizedTest
    @MethodSource("evenBitesCrossoverProvider")
    void shouldProvideEvenBitesCrossover(int gene1Val, int gene2Val, char gene1ValueExpected, char gene2ValueExpected) {
        // given
        RandomProvider randomProvider = new RandomProviderImpl();
        CrossoverService evenBitesService = new EvenBitesCrossoverServiceImpl();
        Gene gene1 = new Gene(randomProvider);
        Gene gene2 = new Gene(randomProvider);
        gene1.setValue(gene1Val);
        gene2.setValue(gene2Val);

        // when
        evenBitesService.cross(gene1, gene2);

        // then
        Assertions.assertEquals(gene1.getValue(), gene1ValueExpected);
        Assertions.assertEquals(gene2.getValue(), gene2ValueExpected);
    }


    private Stream<Arguments> evenBitesCrossoverProvider() {
        return Stream.of(
                Arguments.of(4505, 36, (char) 140, (char) 4401),
                Arguments.of(33, 5000, (char) 4384, (char) 649),
                Arguments.of(70, 65535, (char) 21847, (char) 43758)
        );
    }
}
