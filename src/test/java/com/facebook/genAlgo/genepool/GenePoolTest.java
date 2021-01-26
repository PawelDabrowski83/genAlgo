package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.mutator.MutatorService;
import com.facebook.genAlgo.utils.RandomProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

class GenePoolTest {

    @Mock
    RandomProvider randomProvider;

    @Mock
    MutatorService mutatorService;

    @Mock
    Evaluator evaluator;

    @DisplayName("Should initialize poolOfGene when GenePool constructor is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 40, 55, 287})
    public void shouldInitializeGenes(int sizeExpected) {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, sizeExpected);

        // when
        List<Gene> poolOfGenes = genePool.getPoolOfGenes();

        // then
        Assertions.assertEquals(sizeExpected, poolOfGenes.size());
    }

    @DisplayName("Should perform mutation of each gene when makeMutation() is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldPerformMutation(int sizeExpected) {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, sizeExpected);

        // when
        genePool.makeMutation();

        // then
        verify(mutatorService, times(sizeExpected)).mutate(anyObject());
    }

    @DisplayName("Should perform evaluation of each gene when evaluateFitness() is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldPerformEvaluation(int sizeExpected) {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, sizeExpected);

        // when
        genePool.evaluateFitness();

        // then
        verify(evaluator, times(sizeExpected)).setFitness(anyObject());
    }

    @DisplayName("Should increase generation counter when performEvolution() is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldIncreaseGenerationWhenPerformEvolution(int generation) {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, 10);
        genePool.setGeneration(generation);

        // when
        genePool.performEvolution();

        // then
        assertEquals(++generation, genePool.getGeneration());
    }
}