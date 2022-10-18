package src.main.java.goosegame.impl;

import src.main.java.goosegame.model.Player;
import java.util.*;

public class GooseGame {

    private static final Scanner scanner = new Scanner(System.in);

    private final Set<Integer> goose;
    private final int bridge;
    private final Map<Integer, Player> playersPos;

    public GooseGame(Set<Integer> goose, int bridge, Map<Integer, Player> playersPos) {
        this.goose = goose;
        this.bridge = bridge;
        this.playersPos = playersPos;
    }

    public Player playGooseGame() {
        Sanitize sanitize = new Sanitize();
        int maxPlayers = sanitize.sanitizeToPositiveNum(scanner);
        List<Player> players = addPlayers(maxPlayers);
        return playGame(players);
    }

    private Player playGame(List<Player> players) {
        int finish = 15;
        int i = 0;
        int len = players.size();

        ScoreTracker tracker = new ScoreTracker(goose, bridge, playersPos);
        Dice dice = new Dice();

        while (true) {
            Player player = players.get(i % len);
            int[] rolls = dice.rollDice(scanner);
            int firstNum = rolls[0];
            int secondNum = rolls[1];

            tracker.computeScore(players, player, firstNum, secondNum, finish, i);
            if (player.getScore() == finish) {
                return player;
            }
            i += 1;
        }
    }

    private static List<Player> addPlayers(int num) {
        Set<String> set = new LinkedHashSet<>();
        List<Player> players = new ArrayList<>();
        String addPlayer = "add player ";
        String playerExists = "%s: already existing player";
        StringBuilder builder = new StringBuilder();
        builder.append("players: ");

        while (num > 0) {
            System.out.print(addPlayer);
            String name = scanner.nextLine();
            if (set.contains(name)) {
                System.out.printf(playerExists, name);
                System.out.println();
            } else {
                builder.append(name);
                System.out.println(builder);
                builder.append(", ");
                Player player = new Player();
                player.setName(name);
                set.add(name);
                players.add(player);
                num -= 1;
            }
        }
        return players;
    }
}
