package com.facebook.genAlgo.utils;

public class BitwiseUtils {

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
        if (getBit(number, index) != value) {

        }
        return number;
    }
}
