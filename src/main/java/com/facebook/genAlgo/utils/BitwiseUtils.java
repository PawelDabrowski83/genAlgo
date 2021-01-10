package com.facebook.genAlgo.utils;

public class BitwiseUtils {

    public static final int BIT_MASK = 0b1111_1111_1111_1111_1111_1111_1111_1111;

    public int getBit(int number, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be below zero.");
        }
        return (number >> index) & 1;
    }

    public int setBit(int number, int index, int value) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be below zero.");
        }
        if (value != 0 && value != 1) {
            throw new IllegalArgumentException("Bit value can be only 0 or 1.");
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
}
