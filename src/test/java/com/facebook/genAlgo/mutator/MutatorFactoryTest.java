package com.facebook.genAlgo.mutator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static com.facebook.genAlgo.mutator.MutatorConfig.*;

public class MutatorFactoryTest {

    @DisplayName("Should return SingleMutator when getMutator() method without parameters is called")
    @Test
    public void shouldReturnSingleMutatorWhenGetMutatorIsEvoked() {
        // when
        MutatorService mutator = mutatorFactory.getMutator();

        // then
        assertThat(mutator).isInstanceOf(SingleMutator.class);
    }

    @DisplayName("Should return SingleMutator with mutationChance equal default value when getMutator() method without parameter is called")
    @Test
    public void shouldReturnSingleMutatorWithDefaultMutationChanceWhenGetMutatorIsEvoked() {
        // when
        MutatorService mutator = mutatorFactory.getMutator();

        // then
        assertThat(((SingleMutator)mutator).getMutationChance()).isEqualTo(DEFAULT_MUTATION_CHANCE);
    }

    @DisplayName("Should return instance of MultipleMutator when getMutator() with MULTIPLE enum as parameter is called")
    @Test
    public void shouldReturnInstanceOfMultipleMutatorWhenGetMutatorIsEvoked() {
        // given
        float mutationChance = 0.2f;
        MutatorEnum option = MutatorEnum.MULTIPLE;

        // when
        MutatorService mutator = mutatorFactory.getMutator(mutationChance, option);

        // then
        assertThat(mutator).isInstanceOf(MultipleMutator.class);
    }

    @DisplayName("Should return MultipleMutator with given mutationChance when getMutator() with MULTIPLE enum as parameter is called")
    @ParameterizedTest
    @ValueSource(floats = {0.3f, 0.1f, 0.157f, 0, 1, 0.99f, 0.0001f})
    public void shouldReturnMultipleMutatorWithGivenMutationChanceWhenGetMutatorIsEvoked(float mutationChance) {
        // when
        MutatorService mutator = mutatorFactory.getMutator(mutationChance, MutatorEnum.MULTIPLE);

        // then
        assertThat(((MultipleMutator)mutator).getMutationChance()).isEqualTo(mutationChance);
    }

    @DisplayName("Should return SingleMutator when getMutator() with DEFAULT enum as parameter is called")
    @ParameterizedTest
    @ValueSource(floats = {0.3f, 0.1f, 0.157f, 0, 1, 0.99f, 0.0001f})
    public void shouldReturnSingleMutatorWhenGetMutatorIsEvokedWithDefaultEnumParameter(float mutationChance) {
        // when
        MutatorService mutator = mutatorFactory.getMutator(mutationChance, MutatorEnum.DEFAULT);

        // then
        assertThat(mutator).isInstanceOf(SingleMutator.class);
    }

    @DisplayName("Should given mutationChance override default while DEFAULT enum as parameter is called")
    @ParameterizedTest
    @ValueSource(floats = {0.3f, 0.1f, 0.157f, 0, 1, 0.99f, 0.0001f})
    public void shouldReturnSingleMutatorWithGivenMutationChanceWhenGetMutatorIsEvokedWithDefaultEnumParameter(float mutationChance) {
        // given
        option = MutatorEnum.DEFAULT;

        // when
        MutatorService mutator = mutatorFactory.getMutator(mutationChance, option);

        // then
        assertThat(((SingleMutator)mutator).getMutationChance()).isEqualTo(mutationChance);
    }

    @DisplayName("Should return SingleMutator when getMutator() with ZERO enum and mutationChance equal 0 as parameter is called")
    @Test
    public void shouldReturnSingleMutatorWhenGetMutatorIsEvokedWithZeroEnumParameter() {
        // given
        float mutationChance = 0f;
        option = MutatorEnum.ZERO;

        // when
        MutatorService mutator = mutatorFactory.getMutator(mutationChance, option);

        // then
        assertThat(mutator).isInstanceOf(SingleMutator.class);
    }

    @DisplayName("Should throw exception when getMutator() with conflicting ZERO enum as parameter and nonzero mutationChance is called")
    @ParameterizedTest
    @ValueSource(floats = {0.3f, 0.1f, 0.157f, 1, 0.99f, 0.0001f})
    public void shouldThrowExceptionWhenGetMutatorIsEvokedWithNonZeroMutationChanceAndZeroEnumParameter(float mutationChance) {
        // given
        option = MutatorEnum.ZERO;

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> mutatorFactory.getMutator(mutationChance, option));
    }

    @DisplayName("Should return correct instance of Mutator when getMutator() with single parameter is called")
    @ParameterizedTest
    @MethodSource("singleParamMutatorFactoryArgumentsProvider")
    public void shouldReturnCorrectInstanceOfMutatorWhenGetMutatorWithSingleParamIsEvoked(MutatorEnum option, Class<MutatorService> expected) {
        // when
        MutatorService mutator = mutatorFactory.getMutator(option);

        // then
        assertThat(mutator).isInstanceOf(expected);
    }

    @DisplayName("Should return instance of Mutator with properly set mutation chance with single parameter of getMutator()")
    @ParameterizedTest
    @MethodSource("singleParamMutatorFactoryArgumentsProvider")
    public void shouldReturnCorrectMutationChanceWhenGetMutatorWithSingleParamIsEvoked(MutatorEnum option, Class<MutatorService> mutatorClass, float expectedMutationChance) {
        // when
        MutatorService mutator = mutatorFactory.getMutator(option);
        float actualMutationChance;
        if (mutator instanceof SingleMutator) {
            actualMutationChance = ((SingleMutator) mutator).getMutationChance();
        } else {
            actualMutationChance = ((MultipleMutator) mutator).getMutationChance();
        }

        // then
        assertThat(expectedMutationChance).isEqualTo(actualMutationChance);
    }

    private static Stream<Arguments> singleParamMutatorFactoryArgumentsProvider() {
        return Stream.of(
                Arguments.of(MutatorEnum.MULTIPLE, MultipleMutator.class, DEFAULT_MUTATION_CHANCE),
                Arguments.of(MutatorEnum.SINGLE, SingleMutator.class, DEFAULT_MUTATION_CHANCE),
                Arguments.of(MutatorEnum.ZERO, SingleMutator.class, MutatorFactory.ZERO_MUTATION_CHANCE),
                Arguments.of(MutatorEnum.DEFAULT, SingleMutator.class, DEFAULT_MUTATION_CHANCE)
        );
    }

}
