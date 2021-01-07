package com.facebook.genAlgo.crossover;

import com.facebook.genAlgo.gene.Gene;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

public class MixingHalvesCrossoverServiceImplTest {

    @Mock
    Gene gene1, gene2;

    CrossoverService crossoverService;

    @ParameterizedTest
    void shouldMixingHalvesOfGene(char gene1Val, char gene2Val, char) {

        crossoverService = new MixingHalvesCrossoverImpl();

        when(gene1.getValue()).thenReturn('a');
        when(gene2.getValue()).thenReturn('b');

        crossoverService.cross(gene1, gene2);


    }
}
