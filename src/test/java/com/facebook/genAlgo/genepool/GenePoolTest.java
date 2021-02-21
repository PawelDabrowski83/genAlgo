package com.facebook.genAlgo.genepool;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenePoolTest {

    GenePoolService genePoolService = mock(GenePoolService.class);

    @DisplayName("Should increase generation counter when performEvolution() is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldIncreaseGenerationWhenPerformEvolution(int generation) {
        // given
        GenePool genePool = new GenePool(genePoolService);
        genePool.setGeneration(generation);

        // when
        genePool.performEvolution();

        // then
        assertEquals(++generation, genePool.getGeneration());
    }
}