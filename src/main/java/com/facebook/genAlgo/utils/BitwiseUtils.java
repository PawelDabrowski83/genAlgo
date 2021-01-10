package com.facebook.genAlgo.utils;

public class BitwiseUtils {

    public static final int BIT_MASK = 0b1111_1111_1111_1111_1111_1111_1111_1111;
    public static final String INDEX_BELOW_ZERO_EXCEPTION = "Index cannot be below zero.";
    public static final String INCORRECT_BIT_VALUE_EXCEPTION = "Bit value can be only 0 or 1.";

    public int getBit(int number, int index) {
        if (index < 0) {
            throw new IllegalArgumentException(INDEX_BELOW_ZERO_EXCEPTION);
        }
        return (number >> index) & 1;
    }

    public int setBit(int number, int index, int value) {
        if (index < 0) {
            throw new IllegalArgumentException(INDEX_BELOW_ZERO_EXCEPTION);
        }
        if (value != 0 && value != 1) {
            throw new IllegalArgumentException(INCORRECT_BIT_VALUE_EXCEPTION);
        }
        int currentBitValue = getBit(number, index);
        if (currentBitValue != value) {
            if (currentBitValue == 0) {
                number = number | (1 << index);
            } else {
                int clearMask = BIT_MASK ^ (1 << index);
                number = number & clearMask;
            }
        }
        return number;
    }

    public int getByte(int number, int index) {
        if (index < 0) {
            throw new IllegalArgumentException(INDEX_BELOW_ZERO_EXCEPTION);
        }
        return 0;
    }

    public int setByte(int number, int index, int value) {
        if (index < 0) {
            throw new IllegalArgumentException(INDEX_BELOW_ZERO_EXCEPTION);
        }
        return number;
    }
}
