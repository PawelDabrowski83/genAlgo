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

    @DisplayName("Should setBit() work properly")
    @ParameterizedTest
    @MethodSource("setBitArgumentsProvider")
    void setBit(int expected, int givenNumber, int bitIndex) {
        // when
        int modifiedNumber = bitwiseUtils.setBit(givenNumber, bitIndex, expected);
        int actual = bitwiseUtils.getBit(modifiedNumber, bitIndex);

        // then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> setBitArgumentsProvider() {
        return Stream.of(
                Arguments.of(1, 0b1100_0011, 0),
                Arguments.of(0, 0b1100_0011, 1),
                Arguments.of(1, 0b1100_0011, 2),
                Arguments.of(1, 0b1100_0011, 3),
                Arguments.of(1, 0b1100_0011, 6),
                Arguments.of(1, 0b1100_0011, 33),
                Arguments.of(0, Integer.MIN_VALUE, 31),
                Arguments.of(1, Integer.MAX_VALUE, 31)
        );
    }

    @DisplayName("Should setBit() throw IllegalArgumentException if arguments are invalid")
    @ParameterizedTest
    @MethodSource("setBitThrowsExceptionArgumentsProvider")
    void setBitThrowsException(Exception expected, int givenNumber, int bitIndex, int bitValue) {
        assertThrows(expected.getClass(), () -> bitwiseUtils.setBit(givenNumber, bitIndex, bitValue));
    }

    private static Stream<Arguments> setBitThrowsExceptionArgumentsProvider() {
        return Stream.of(
                Arguments.of(new IllegalArgumentException(), Integer.MAX_VALUE, -1, 1),
                Arguments.of(new IllegalArgumentException(), Integer.MAX_VALUE, Integer.MIN_VALUE, 1),
                Arguments.of(new IllegalArgumentException(), Integer.MAX_VALUE, 0, 2),
                Arguments.of(new IllegalArgumentException(), 0, 10, Integer.MIN_VALUE),
                Arguments.of(new IllegalArgumentException(), 0b1111, 10, Integer.MAX_VALUE)
        );
    }

    @DisplayName("Should getByte() work properly")
    @ParameterizedTest
    @MethodSource("getByteArgumentsProvider")
    void getByte(int expected, int givenNumber, int byteIndex) {
        assertEquals(expected, bitwiseUtils.getByte(givenNumber, byteIndex));
    }

    private static Stream<Arguments> getByteArgumentsProvider() {
        return Stream.of(
                Arguments.of(0b1001_1111, 0b1001_1111, 0),
                Arguments.of(0, 0b1001_1111, 1),
                Arguments.of(0b0011_1001, 0b1001_1111_0011_1001, 0),
                Arguments.of(0b1001_1111, 0b1001_1111_0011_1001, 1),
                Arguments.of(0, Integer.MIN_VALUE, 0),
                Arguments.of(0, Integer.MIN_VALUE, 1),
                Arguments.of(0, Integer.MIN_VALUE, 2),
                Arguments.of(0b1000_0000, Integer.MIN_VALUE, 3),
                Arguments.of(0b1111_1111, Integer.MAX_VALUE, 0),
                Arguments.of(0b1111_1111, Integer.MAX_VALUE, 1),
                Arguments.of(0b1111_1111, Integer.MAX_VALUE, 2),
                Arguments.of(0b0111_1111, Integer.MAX_VALUE, 3),
                Arguments.of(0, 0, 0),
                Arguments.of(0, 0, 1),
                Arguments.of(0, 0, 2),
                Arguments.of(0, 0, 3)
        );
    }

    @DisplayName("Should getByte throw IllegalArgumentException given invalid arguments")
    @ParameterizedTest
    @MethodSource("getByteThrowsExceptionArgumentsProvider")
    void getByteThrowsException(Exception expected, int givenNumber, int byteIndex) {
        assertThrows(expected.getClass(), () -> bitwiseUtils.getByte(givenNumber, byteIndex));
    }

    private static Stream<Arguments> getByteThrowsExceptionArgumentsProvider() {
        return Stream.of(
                Arguments.of(new IllegalArgumentException(), 0, -1),
                Arguments.of(new IllegalArgumentException(), 0, Integer.MIN_VALUE),
                Arguments.of(new IllegalArgumentException(), Integer.MIN_VALUE, -1),
                Arguments.of(new IllegalArgumentException(), Integer.MIN_VALUE, Integer.MIN_VALUE),
                Arguments.of(new IllegalArgumentException(), Integer.MAX_VALUE, -1),
                Arguments.of(new IllegalArgumentException(), Integer.MAX_VALUE, Integer.MIN_VALUE)
        );
    }

    @DisplayName("Should setByte() work properly")
    @ParameterizedTest
    @MethodSource("setByteArgumentsProvider")
    void setByte(int expected, int givenNumber, int byteIndex) {
        // when
        int modifiedNumber = bitwiseUtils.setByte(givenNumber, byteIndex, expected);
        int actual = bitwiseUtils.getByte(modifiedNumber, byteIndex);

        // then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> setByteArgumentsProvider() {
        return Stream.of(
                Arguments.of(0b1111_1111, 0, 0),
                Arguments.of(0b0101_1100, 0, 0),
                Arguments.of(0b1111_1111, 0, 1),
                Arguments.of(0b0101_1100, 0, 1),
                Arguments.of(0, Integer.MAX_VALUE, 0),
                Arguments.of(0, Integer.MAX_VALUE, 1),
                Arguments.of(0, Integer.MAX_VALUE, 2),
                Arguments.of(0, Integer.MAX_VALUE, 3),
                Arguments.of(0, Integer.MAX_VALUE, 4),
                Arguments.of(0b1111_1111, Integer.MAX_VALUE, 0),
                Arguments.of(0b1111_1111, Integer.MAX_VALUE, 1),
                Arguments.of(0b1111_1111, Integer.MAX_VALUE, 2),
                Arguments.of(0b1111_1111, Integer.MAX_VALUE, 3),
                Arguments.of(0b1111_1111, Integer.MAX_VALUE, 4)
        );
    }

    @DisplayName("Should setByte() throw IllegalArgumentException given wrong arguments")
    @ParameterizedTest
    @MethodSource("setByteThrowsExceptionArgumentsProvider")
    void setByteThrowsException(Exception expected, int givenNumber, int byteIndex, int byteValue) {
        assertThrows(expected.getClass(), () -> bitwiseUtils.setByte(givenNumber, byteIndex, byteValue));
    }

    private static Stream<Arguments> setByteThrowsExceptionArgumentsProvider() {
        return Stream.of(
                Arguments.of(new IllegalArgumentException(), 0, -1, 0),
                Arguments.of(new IllegalArgumentException(), 0, Integer.MIN_VALUE, 0),
                Arguments.of(new IllegalArgumentException(), 0, Integer.MIN_VALUE, Integer.MAX_VALUE),
                Arguments.of(new IllegalArgumentException(), 0, 2, Integer.MAX_VALUE),
                Arguments.of(new IllegalArgumentException(), Integer.MIN_VALUE, -1, 0),
                Arguments.of(new IllegalArgumentException(), Integer.MIN_VALUE, Integer.MIN_VALUE, 0),
                Arguments.of(new IllegalArgumentException(), Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE),
                Arguments.of(new IllegalArgumentException(), Integer.MIN_VALUE, 2, Integer.MAX_VALUE),
                Arguments.of(new IllegalArgumentException(), Integer.MAX_VALUE, -1, 0),
                Arguments.of(new IllegalArgumentException(), Integer.MAX_VALUE, Integer.MIN_VALUE, 0),
                Arguments.of(new IllegalArgumentException(), Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE),
                Arguments.of(new IllegalArgumentException(), Integer.MAX_VALUE, 2, Integer.MAX_VALUE)
        );
    }
}
