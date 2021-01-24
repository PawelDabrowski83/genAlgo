package com.facebook.genAlgo.genepool;

import com.facebook.genAlgo.evaluator.Evaluator;
import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.mutator.MutatorService;
import com.facebook.genAlgo.utils.RandomProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GenePoolTest {

    RandomProvider randomProvider;
    MutatorService mutatorService;
    Evaluator evaluator;

    Gene gene1 = new Gene(randomProvider);

    List<Gene> testGenePool = Arrays.asList(new Gene(randomProvider),
            new Gene(randomProvider),
            new Gene(randomProvider),
            new Gene(randomProvider),
            new Gene(randomProvider));


    @Test
    @ValueSource(ints = {2, 10, 40, 55, 287})
    public void shouldInitializedGenes(int sizeExpected) {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, sizeExpected);

        // when
        List<Gene> poolOfGenes = genePool.getPoolOfGenes();

        // then
        Assertions.assertEquals(sizeExpected, poolOfGenes.size());
    }

    @Test
    public void shouldPerformMutation() {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, 5);
        List<Gene> poolOfGenes = genePool.getPoolOfGenes();
        List<Character> initialGeneValue = poolOfGenes.stream()
                .map(gene -> gene.getValue())
                .collect(Collectors.toList());

        when(randomProvider.getFloat()).thenReturn(1F);

        // when
        genePool.makeMutation();
        List<Gene> poolOfGenesMutated = genePool.getPoolOfGenes();
        List<Character> mutatedGeneValue = poolOfGenesMutated.stream()
                .map(gene -> gene.getValue())
                .collect(Collectors.toList());

        // then
        assertFalse(mutatedGeneValue.contains(initialGeneValue));
    }

    public void shouldPerformEvaluation() {
        // given
        GenePool genePool = new GenePool(randomProvider, mutatorService, evaluator, 5);

        // when
    }
}