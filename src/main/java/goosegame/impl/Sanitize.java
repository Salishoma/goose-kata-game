package src.main.java.goosegame.impl;

import java.util.Scanner;

public class Sanitize {

    int sanitizeToPositiveNum(Scanner scanner) {
        System.out.println("Add number of players");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid number");
            System.out.println("Add number of players");
            scanner.nextLine();
        }
        int res = scanner.nextInt();
        scanner.nextLine();

        while (res <= 0 || res > 5) {
            System.out.println("Only numbers between 1 to 5 (both inclusive) allowed");
            res = sanitizeToPositiveNum(scanner);
        }
        return res;
    }
}
