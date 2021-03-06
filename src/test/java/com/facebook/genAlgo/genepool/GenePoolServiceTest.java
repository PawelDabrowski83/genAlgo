package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.crossover.CrossoverHandler;
import com.facebook.genAlgo.crossover.CrossoverService;
import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.mutator.MutatorService;
import com.facebook.genAlgo.solutionfinder.SolutionFinder;
import com.facebook.genAlgo.utils.RandomProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GenePoolServiceTest {

    RandomProvider randomProvider = mock(RandomProvider.class);
    MutatorService mutatorService = mock(MutatorService.class);
    CrossoverService crossoverService = mock(CrossoverService.class);
    SolutionFinder solutionFinder = mock(SolutionFinder.class);
    Evaluator evaluator = mock(Evaluator.class);
    CrossoverHandler crossoverHandler = spy(new CrossoverHandler(crossoverService));
    List<Gene> geneTestList = new ArrayList<>();

    @BeforeEach
    public void initializeGeneList() {
        geneTestList.addAll(List.of(new Gene(randomProvider),
                new Gene(randomProvider),
                new Gene(randomProvider),
                new Gene(randomProvider),
                new Gene(randomProvider),
                new Gene(randomProvider)
                ));
    }

    @DisplayName("Should create list of genes when initializeGenes method was called")
    @ParameterizedTest
    @ValueSource(ints = {10, 30, 40, 100, 500})
    public void shouldCreateListOfGenesWhenInitializeGenesMethodWasCalled(int poolSize) {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutatorService, evaluator, crossoverHandler,
                solutionFinder);

        // when
        List<Gene> genes = genePoolService.initializeGenes(poolSize);

        // then
        assertThat(genes.size()).isEqualTo(poolSize);
    }

    @DisplayName("Should perform mutation given number of times when makeMutation is called")
    @Test
    public void shouldPerformMutation() {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutatorService, evaluator, crossoverHandler,
                solutionFinder);

        // when
        genePoolService.makeMutation(geneTestList);

        // then
        verify(mutatorService, times(geneTestList.size())).mutate(any());
    }

    @DisplayName("Should perform mutate method on each gene when makeMutation is called")
    @Test
    public void shouldPerformMutationOnEachGene() {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutatorService, evaluator, crossoverHandler,
                solutionFinder);
        ArgumentCaptor<Gene> geneCaptor = ArgumentCaptor.forClass(Gene.class);

        // when
        genePoolService.makeMutation(geneTestList);
        verify(mutatorService, times(geneTestList.size())).mutate(geneCaptor.capture());
        List<Gene> allCapturedGenes = geneCaptor.getAllValues();

        List<Gene> distinctGeneList = allCapturedGenes.stream()
                .distinct()
                .collect(Collectors.toList());

        // then
        assertThat(distinctGeneList.size()).isEqualTo(geneTestList.size());
    }

    @DisplayName("Should perform evaluation given number of times when evaluateFitness is called")
    @Test
    public void shouldPerformEvaluation() {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutatorService, evaluator, crossoverHandler,
                solutionFinder);

        // when
        genePoolService.evaluateFitness(geneTestList);

        // then
        verify(evaluator, times(geneTestList.size())).setFitness(any());
    }

    @DisplayName("Should perform evaluation on each gene when evaluateFitness is called")
    @Test
    public void shouldPerformEvaluationOnEachGene() {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutatorService, evaluator, crossoverHandler,
                solutionFinder);
        ArgumentCaptor<Gene> geneCaptor = ArgumentCaptor.forClass(Gene.class);

        // when
        genePoolService.evaluateFitness(geneTestList);
        verify(evaluator, times(geneTestList.size())).setFitness(geneCaptor.capture());
        List<Gene> allCapturedGenes = geneCaptor.getAllValues();

        List<Gene> distinctGeneList = allCapturedGenes.stream()
                .distinct()
                .collect(Collectors.toList());

        // then
        assertThat(distinctGeneList.size()).isEqualTo(geneTestList.size());
    }

    @DisplayName("Should perform cross when makeCross method is called")
    @Test
    public void shouldPerformCrossWhenMakeCrossIsCalled() {
        // given
        GenePoolService genePoolService = new GenePoolService(randomProvider, mutatorService, evaluator, crossoverHandler,
                solutionFinder);

        // when
        genePoolService.makeCross(geneTestList);

        // then
        verify(crossoverHandler, times(1)).performCross(anyList());
    }
}