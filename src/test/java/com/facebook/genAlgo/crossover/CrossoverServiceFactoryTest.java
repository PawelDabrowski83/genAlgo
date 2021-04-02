package com.facebook.genAlgo.crossover;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.facebook.genAlgo.crossover.CrossoverServiceFactory.*;

public class CrossoverServiceFactoryTest {

    CrossoverServiceFactory crossoverServiceFactory;

    @BeforeEach
    public void setUp() {
        crossoverServiceFactory = new CrossoverServiceFactory();
    }

    @DisplayName("Should return default instance of CrossoverService when getCrossoverService method W/O parameter is called")
    @Test
    public void shouldReturnDefaultInstanceOfCrossoverService() {
        // when
        CrossoverService crossoverService = crossoverServiceFactory.getCrossoverService();

        // then
        Assertions.assertThat(crossoverService).isInstanceOf(BitPairCrossoverServiceImpl.class);
    }

    @DisplayName("Should return proper instance of CrossoverService when getCrossoverService with enum parameter is called")
    @ParameterizedTest
    @MethodSource
    public void shouldReturnProperInstanceOfCrossoverService(Class<? extends CrossoverService> instanceType, CrossoverEnum option) {
        // when
        CrossoverService crossoverService = crossoverServiceFactory.getCrossoverService(option);

        // then
        Assertions.assertThat(crossoverService).isInstanceOf(instanceType);
    }

    private static Stream<Arguments> shouldReturnProperInstanceOfCrossoverService() {
        return Stream.of(
                Arguments.of(BitPairCrossoverServiceImpl.class, CrossoverEnum.DEFAULT),
                Arguments.of(BitPairCrossoverServiceImpl.class, CrossoverEnum.BIT_PAIR),
                Arguments.of(EvenBitsCrossoverServiceImpl.class, CrossoverEnum.EVEN_BITS),
                Arguments.of(MixingHalvesCrossoverServiceImpl.class, CrossoverEnum.MIXING_HALVES),
                Arguments.of(OddBitsCrossoverServiceImpl.class, CrossoverEnum.ODD_BITS)
        );
    }
}
