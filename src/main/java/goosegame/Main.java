package src.main.java.goosegame;

import src.main.java.goosegame.impl.GooseGame;
import src.main.java.goosegame.model.Player;

import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Set<Integer> goose = new HashSet<>(Arrays.asList(5, 9, 14, 18, 23, 27));
    private static final int BRIDGE = 6;
    private static Map<Integer, Player> playersPos = new HashMap<>();

    public static void main(String[] args) {

        GooseGame gooseGame = new GooseGame(goose, BRIDGE, playersPos);
        Player winner = gooseGame.playGooseGame();

        System.out.println(". " + winner.getName() + " Wins!!");

        System.out.println(winner);

    }
}
