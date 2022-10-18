package src.main.java.goosegame.impl;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Dice {

     int[] rollDice(Scanner scanner) {
        System.out.println();
        Pattern pattern = Pattern.compile("\\d+");
        int firstNum = 0;
        int secondNum = 0;
        String numWords = scanner.nextLine();
        String[] split = numWords.split("[ ,]");

        for (String s : split) {
            if (pattern.matcher(s).matches()) {
                int num = Math.min(Math.abs(Integer.parseInt(s)), 6);
                if (firstNum == 0) {
                    firstNum = num;
                } else {
                    secondNum = num;
                    break;
                }
            }
        }

        if (firstNum == 0) {
            firstNum = (int)(Math.random() * 6) + 1;
            secondNum = (int)(Math.random() * 6) + 1;
        } else if (secondNum == 0) {
            secondNum = (int)(Math.random() * 6) + 1;
        }
        return new int[]{firstNum, secondNum};
    }
}
