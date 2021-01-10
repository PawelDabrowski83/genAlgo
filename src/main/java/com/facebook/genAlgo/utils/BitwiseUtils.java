package com.facebook.genAlgo.utils;

public class BitwiseUtils {

    public int getBit(int number, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be below zero.");
        }
        return (number >> index) & 1;
    }
}
