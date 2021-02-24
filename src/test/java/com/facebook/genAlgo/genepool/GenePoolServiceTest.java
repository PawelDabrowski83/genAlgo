package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.crossover.CrossoverHandler;
import com.facebook.genAlgo.crossover.CrossoverService;
import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.mutator.MutatorService;
import com.facebook.genAlgo.utils.RandomProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GenePoolServiceTest {

    RandomProvider randomProvider = mock(RandomProvider.class);
    MutatorService mutatorService = mock(MutatorService.class);
    CrossoverService crossoverService = mock(CrossoverService.class);
    Evaluator evaluator = mock(Evaluator.class);
    CrossoverHandler crossoverHandler = spy(new CrossoverHandler(crossoverService));

    @DisplayName("Should initialize poolOfGene when GenePool constructor is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 40, 55, 287})
    public void shouldInitializeGenes(int sizeExpected) {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutatorService, evaluator, crossoverHandler, sizeExpected);

        // when
        List<Gene> poolOfGenes = genePoolService.getPoolOfGenes();

        // then
        Assertions.assertEquals(sizeExpected, poolOfGenes.size());
    }

    @DisplayName("Should perform mutation given number of times when makeMutation is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldPerformMutation(int sizeExpected) {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutatorService, evaluator, crossoverHandler, sizeExpected);

        // when
        genePoolService.makeMutation();

        // then
        verify(mutatorService, times(sizeExpected)).mutate(anyObject());
    }

    @DisplayName("Should perform mutate method on each gene when makeMutation is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldPerformMutationOnEachGene(int sizeExpected) {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutatorService, evaluator, crossoverHandler, sizeExpected);
        ArgumentCaptor<Gene> geneCaptor = ArgumentCaptor.forClass(Gene.class);

        // when
        genePoolService.makeMutation();
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
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutatorService, evaluator, crossoverHandler, sizeExpected);

        // when
        genePoolService.evaluateFitness();

        // then
        verify(evaluator, times(sizeExpected)).setFitness(anyObject());
    }

    @DisplayName("Should perform evaluation on each gene when evaluateFitness is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 55, 1000})
    public void shouldPerformEvaluationOnEachGene(int sizeExpected) {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutatorService, evaluator, crossoverHandler, sizeExpected);
        ArgumentCaptor<Gene> geneCaptor = ArgumentCaptor.forClass(Gene.class);

        // when
        genePoolService.evaluateFitness();
        verify(evaluator, times(sizeExpected)).setFitness(geneCaptor.capture());
        List<Gene> allCapturedGenes = geneCaptor.getAllValues();

        List<Gene> distinctGeneList = allCapturedGenes.stream()
                .distinct()
                .collect(Collectors.toList());

        // then
        assertEquals(distinctGeneList.size(), sizeExpected);
    }

    @DisplayName("Should perform cross when makeCross method is called")
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 30, 56, 1000})
    public void shouldPerformCrossWhenMakeCrossIsCalled(int sizeExpected) {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutatorService, evaluator, crossoverHandler, sizeExpected);

        // when
        genePoolService.makeCross();

        // then
        verify(crossoverHandler, times(1)).performCross(anyList());
    }

}