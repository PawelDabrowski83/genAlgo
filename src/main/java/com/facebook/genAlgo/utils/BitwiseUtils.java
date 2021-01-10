package com.facebook.genAlgo.utils;

public class BitwiseUtils {

    public int getBit(int number, int index) {
        return (number >> index) & 1;
    }
}
