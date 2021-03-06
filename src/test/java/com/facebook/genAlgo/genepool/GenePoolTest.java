package com.facebook.genAlgo.genepool;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenePoolTest {

    GenePoolService genePoolService = mock(GenePoolService.class);


    @DisplayName("Should initialize List of genes when GenePool is created")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldInitializeListOfGenesWhenGenePoolIsCreated(int size) {
        // given
        GenePool genePool = new GenePool(genePoolService, size);

        // then
        assertThat(genePool.getPoolOfGenes().size()).isEqualTo(size);
    }

    @DisplayName("Should increase generation counter when performEvolution() is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldIncreaseGenerationWhenPerformEvolution(int generation) {
        // given
        GenePool genePool = new GenePool(genePoolService, 10);
        genePool.setGeneration(generation);

        // when
        genePool.performEvolution();

        // then
        assertEquals(++generation, genePool.getGeneration());
    }
}