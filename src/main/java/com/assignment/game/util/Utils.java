package com.assignment.game.util;

import java.util.Random;

public class Utils {

    public static int getRandomNumber(final int min, final int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static int getDifferentNumber(int number1, int number2, int min, int max) {
        int number3 = new Random().nextInt(max - min + 1) + min;
        while (number3 == number1 || number3 == number2) {
            number3 = new Random().nextInt(max - min + 1) + min;
        }
        return number3;
    }
}
