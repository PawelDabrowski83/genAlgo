package com.facebook.genAlgo.crossover;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.RandomProvider;
import com.facebook.genAlgo.utils.RandomProviderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class MixingHalvesCrossoverServiceImplTest {

    @ParameterizedTest
    @MethodSource("mixingHalvesCrossoverProvider")
    void shouldProvideMixingHalvesCrossover(char gene1Val, char gene2Val, char gene1ValueExpected, char gene2ValueExpected) {
        // given
        RandomProvider randomProvider = new RandomProviderImpl();
        CrossoverService mixingHalvesService = new MixingHalvesCrossoverServiceImpl();
        Gene gene1 = new Gene(randomProvider);
        Gene gene2 = new Gene(randomProvider);
        gene1.setValue(gene1Val);
        gene2.setValue(gene2Val);

        // when
        mixingHalvesService.cross(gene1, gene2);

        // then
        Assertions.assertEquals(gene1.getValue(), gene1ValueExpected);
        Assertions.assertEquals(gene2.getValue(), gene2ValueExpected);
    }


    private static Stream<Arguments> mixingHalvesCrossoverProvider() {
        return Stream.of(
                Arguments.of((char) 4505, (char) 36, (char) 153, (char) 4388),
                Arguments.of((char) 33, (char) 5000, (char) 4897, (char) 136),
                Arguments.of((char) 70, (char) 65535, (char) 65350, (char) 255)
        );
    }
}
