package com.facebook.genAlgo.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BitwiseUtilsTest {

    BitwiseUtils bitwiseUtils = new BitwiseUtils();

    @DisplayName("Should getBit() return proper bits")
    @ParameterizedTest
    @MethodSource("getBitArgumentsProvider")
    void getBits(int expected, int given, int bitIndex) {
        assertEquals(expected, bitwiseUtils.getBit(given, bitIndex));
    }

    private static Stream<Arguments> getBitArgumentsProvider() {
        return Stream.of(
                Arguments.of(1, 0b1010_0001, 0),
                Arguments.of(0, 0b1010_0001, 1),
                Arguments.of(0, 0b1010_0001, 3),
                Arguments.of(1, 0b1010_0001, 5),
                Arguments.of(0, 0b1010_0001, 6),
                Arguments.of(1, 0b1010_0001, 7),
                Arguments.of(0, 0b1010_0001, 8),
                Arguments.of(1, 0b1010_0001, 32),
                Arguments.of(0, 0b1010_0001, 33),
                Arguments.of(0, 0, 10),
                Arguments.of(0, Integer.MAX_VALUE, 31),
                Arguments.of(1, Integer.MIN_VALUE, 31)
        );
    }

    @DisplayName("Should getBit() throw IllegalArgumentException when given is out of range")
    @ParameterizedTest
    @MethodSource("getBitThrowsExceptionArgumentsProvider")
    void getBitsThrowsException(Exception expected, int given, int bitIndex) {
        assertThrows(expected.getClass(), () -> bitwiseUtils.getBit(given, bitIndex));
    }

    private static Stream<Arguments> getBitThrowsExceptionArgumentsProvider() {
        return Stream.of(
                Arguments.of(new IllegalArgumentException(), 0, -1),
                Arguments.of(new IllegalArgumentException(), 0, -100),
                Arguments.of(new IllegalArgumentException(), 0, Integer.MIN_VALUE)
        );
    }
}
