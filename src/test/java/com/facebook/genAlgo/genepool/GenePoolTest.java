package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.crossover.CrossoverHandler;
import com.facebook.genAlgo.crossover.CrossoverService;
import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.mutator.MutatorService;
import com.facebook.genAlgo.utils.RandomProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

class GenePoolTest {

    RandomProvider randomProvider = mock(RandomProvider.class);
    MutatorService mutatorService = mock(MutatorService.class);
    CrossoverService crossoverService = mock(CrossoverService.class);
    Evaluator evaluator = mock(Evaluator.class);
    CrossoverHandler crossoverHandler = mock(CrossoverHandler.class);

    @DisplayName("Should initialize poolOfGene when GenePool constructor is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 40, 55, 287})
    public void shouldInitializeGenes(int sizeExpected) {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, crossoverHandler, sizeExpected);

        // when
        List<Gene> poolOfGenes = genePool.getPoolOfGenes();

        // then
        Assertions.assertEquals(sizeExpected, poolOfGenes.size());
    }

    @DisplayName("Should perform mutation given number of times when makeMutation is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldPerformMutation(int sizeExpected) {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, crossoverHandler, sizeExpected);

        // when
        genePool.makeMutation();

        // then
        verify(mutatorService, times(sizeExpected)).mutate(anyObject());
    }

    @DisplayName("Should perform mutate method on each gene when makeMutation is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldPerformMutationOnEachGene(int sizeExpected) {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, crossoverHandler, sizeExpected);
        ArgumentCaptor<Gene> geneCaptor = ArgumentCaptor.forClass(Gene.class);

        // when
        genePool.makeMutation();
        verify(mutatorService, times(sizeExpected)).mutate(geneCaptor.capture());
        List<Gene> allCapturedGenes = geneCaptor.getAllValues();

        List<Gene> distinctGeneList = allCapturedGenes.stream()
                .distinct()
                .collect(Collectors.toList());

        // then
        assertEquals(distinctGeneList.size(), sizeExpected);
    }

    @DisplayName("Should perform evaluation given number of times when evaluateFitness is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldPerformEvaluation(int sizeExpected) {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, crossoverHandler, sizeExpected);

        // when
        genePool.evaluateFitness();

        // then
        verify(evaluator, times(sizeExpected)).setFitness(anyObject());
    }

    @DisplayName("Should perform evaluation on each gene when evaluateFitness is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldPerformEvaluationOnEachGene(int sizeExpected) {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, crossoverHandler, sizeExpected);
        ArgumentCaptor<Gene> geneCaptor = ArgumentCaptor.forClass(Gene.class);

        // when
        genePool.evaluateFitness();
        verify(evaluator,times(sizeExpected)).setFitness(geneCaptor.capture());
        List<Gene> allCapturedGenes = geneCaptor.getAllValues();

        List<Gene> distinctGeneList = allCapturedGenes.stream()
                .distinct()
                .collect(Collectors.toList());

        // then
        assertEquals(distinctGeneList.size(), sizeExpected);
    }

    @DisplayName("Should increase generation counter when performEvolution() is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldIncreaseGenerationWhenPerformEvolution(int generation) {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, crossoverHandler, 10);
        genePool.setGeneration(generation);

        // when
        genePool.performEvolution();

        // then
        assertEquals(++generation, genePool.getGeneration());
    }

    @DisplayName("Should perform cross on each pair of gene")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 56, 1000})
    public void shouldPerformCrossOnEachPairOfGene(int sizeExpected) {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, crossoverHandler, sizeExpected);
        ArgumentCaptor<Gene> geneArgumentCaptor = ArgumentCaptor.forClass(Gene.class);

        // when
        genePool.makeCross();
        verify(crossoverService, times(sizeExpected/2))
                .cross(geneArgumentCaptor.capture(), geneArgumentCaptor.capture());

        List<Gene> allCapturedValues = geneArgumentCaptor.getAllValues();

        List<Gene> distinctGenes = allCapturedValues.stream()
                .distinct()
                .collect(Collectors.toList());

        // then
        assertEquals(sizeExpected, distinctGenes.size());
    }

    @DisplayName("Should perform cross on sorted list of gene")
    @Test
    public void shouldPerformCrossOnPoolOfGeneInProperOrder() {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, crossoverHandler, 6);

        ArgumentCaptor<Gene> geneArgumentCaptor = ArgumentCaptor.forClass(Gene.class);

        // when
        genePool.makeMutation();
        verify(crossoverService, times(3))
                .cross(geneArgumentCaptor.capture(), geneArgumentCaptor.capture());

        List<Gene> allCapturedGene = geneArgumentCaptor.getAllValues();

        List<Gene> sortedGeneList = allCapturedGene.stream()
                .sorted(Comparator.comparing(gene -> gene.getFitness()))
                .collect(Collectors.toList());

        // then
        assertEquals(allCapturedGene, sortedGeneList);
    }
}