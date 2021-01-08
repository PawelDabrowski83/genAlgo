package com.facebook.genAlgo.crossover;

import com.facebook.genAlgo.gene.Gene;
import com.sun.jdi.connect.Connector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;

public class MixingHalvesCrossoverServiceImplTest {

    @Mock
    Gene gene1, gene2;

    CrossoverService crossoverService;

    @ParameterizedTest
    @MethodSource("shouldMixingHalvesOfGeneProvider")
    void shouldMixingHalvesOfGene(char gene1Val, char gene2Val, char gene1ValueAfterCross, char gene2ValueAfterCross) {

        crossoverService = new MixingHalvesCrossoverImpl();

        when(gene1.getValue()).thenReturn('a');
        when(gene2.getValue()).thenReturn('b');

        crossoverService.cross(gene1, gene2);

        Assertions.assertEquals();
    }

    private Stream<Arguments> shouldMixingHalvesOfGeneProvider() {
        return Stream.of(
                Arguments.of('ᆙ', '$', (char) 4388, (char) 153),
                Arguments.of('!', 'ᎈ', (char) 136, (char) 4897),
                Arguments.of('F', '\uFFFF', (char) 255, (char) 65350)
        );
    }
}
