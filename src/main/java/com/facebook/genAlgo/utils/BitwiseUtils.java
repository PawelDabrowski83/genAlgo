package com.facebook.genAlgo.utils;

public class BitwiseUtils {

    public static final int FULL_BIT_MASK = 0b1111_1111_1111_1111_1111_1111_1111_1111;
    public static final int BIT_MASK = 0b1111_1111;
    public static final String INDEX_BELOW_ZERO_EXCEPTION = "Index cannot be below zero.";
    public static final String INCORRECT_BIT_VALUE_EXCEPTION = "Bit value can be only 0 or 1.";
    public static final String INCORRECT_BYTE_VALUE_EXCEPTION = "Byte value should not exceed 8 bits.";

    public int getBit(int number, int index) {
        checkIndexNotNegative(index);
        return (number >> index) & 1;
    }

    public int setBit(int number, int index, int value) {
        checkIndexNotNegative(index);
        if (value != 0 && value != 1) {
            throw new IllegalArgumentException(INCORRECT_BIT_VALUE_EXCEPTION);
        }
        int currentBitValue = getBit(number, index);
        if (currentBitValue != value) {
            if (currentBitValue == 0) {
                number = number | (1 << index);
            } else {
                int clearMask = FULL_BIT_MASK ^ (1 << index);
                number = number & clearMask;
            }
        }
        return number;
    }

    public int getByte(int number, int index) {
        checkIndexNotNegative(index);
        return number >> index * 8 & BIT_MASK;
    }

    public int setByte(int number, int index, int value) {
        checkIndexNotNegative(index);
        if (value > 0b1111_1111) {
            throw new IllegalArgumentException(INCORRECT_BYTE_VALUE_EXCEPTION);
        }
        int currentByte = getByte(number, index);
        if (currentByte != value) {
            if (currentByte != 0) {
                int clearMask = FULL_BIT_MASK ^ (BIT_MASK << index * 8);
                number = number & clearMask;
            }
            number = number | (value << index * 8);
        }
        return number;
    }

    private void checkIndexNotNegative(int index) {
        if (index < 0) {
            throw new IllegalArgumentException(INDEX_BELOW_ZERO_EXCEPTION);
        }
    }
}
