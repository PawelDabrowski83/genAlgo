package com.facebook.genAlgo.crossover;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.genepool.GenePool;
import com.facebook.genAlgo.utils.RandomProvider;
import com.facebook.genAlgo.utils.RandomProviderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

class CrossoverHandlerTest {

    CrossoverService crossoverService = mock(CrossoverService.class);

    RandomProvider randomProvider = new RandomProviderImpl();

    List<Gene> geneList = List.of(
            new Gene(randomProvider),
            new Gene(randomProvider),
            new Gene(randomProvider),
            new Gene(randomProvider),
            new Gene(randomProvider),
            new Gene(randomProvider),
            new Gene(randomProvider),
            new Gene(randomProvider)
    );

    @Test
    public void shouldPerformCrossOnEachPairOfGene() {
        // given
        ArrayList<Gene> geneArrayList = new ArrayList<>(geneList);
        CrossoverHandler crossoverHandler = new CrossoverHandler(crossoverService);
        ArgumentCaptor<Gene> geneArgumentCaptor = ArgumentCaptor.forClass(Gene.class);

        //when
        crossoverHandler.performCross(geneArrayList);
        verify(crossoverService, times(geneList.size() / 2))
                .cross(geneArgumentCaptor.capture(), geneArgumentCaptor.capture());

        List<Gene> allValues = geneArgumentCaptor.getAllValues()
                .stream()
                .distinct()
                .collect(Collectors.toList());

        // then
        Assertions.assertEquals(allValues.size(), geneList.size());
    }
}