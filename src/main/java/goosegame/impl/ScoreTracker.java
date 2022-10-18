package src.main.java.goosegame.impl;

import src.main.java.goosegame.model.Player;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScoreTracker {
    private final Set<Integer> goose;
    private final int bridge;
    private final Map<Integer, Player> playersPos;

    ScoreTracker(Set<Integer> goose, int bridge, Map<Integer, Player> playersPos) {
        this.goose = goose;
        this.bridge = bridge;
        this.playersPos = playersPos;
    }


    void computeScore(List<Player> players, Player player, int firstNum,
                              int secondNum, int finish, int ind
    ) {

        String curPlayer = player.getName();
        int playerScore = player.getScore();
        int len = players.size();
        String words = "%s rolls %s, %s. %s moves from %s to %s";
        String jumpFromBridge = "%s jumps to %s";
        String movesAgain = "%s moves again and goes to %s";
        String theGoose = "The Goose. ";

        int newScore = playerScore + firstNum + secondNum;

        String prev;
        if (playerScore == 0) {
            prev = "start";
        } else {
            prev = "" + playerScore;
        }

        String cur = "" + newScore;

        if (newScore == bridge){
            cur = "The Bridge. ";
            newScore = 2 * bridge;
        }

        System.out.printf(words, curPlayer, firstNum, secondNum, curPlayer, prev, cur);

        if ("The Bridge. ".equals(cur)) {
            System.out.printf(jumpFromBridge, curPlayer, newScore);
        }

        while (goose.contains(newScore)) {
            System.out.print(", " + theGoose);
            newScore += firstNum + secondNum;
            System.out.printf(movesAgain, curPlayer, newScore);
        }

        if (newScore > finish) {
            playerScore += 1;
            setPlayersPos(players, player, playerScore);
            System.out.print(". " + curPlayer + " bounces! " + curPlayer + " returns to " + playerScore);
            return;
        }

        setPlayersPos(players, player, newScore);

        players.set(ind % len, player);

    }

    private void setPlayersPos(List<Player> players, Player player, int newScore) {
        String movesBack = ". On %s there is %s, who returns to %s";
        int playerScore = player.getScore();

        playersPos.put(playerScore, null);

        Player nextPlayer = playersPos.get(newScore);
        if (nextPlayer != null) {
            nextPlayer.setScore(playerScore);
            playersPos.put(playerScore, nextPlayer);
            players.set(players.indexOf(player), nextPlayer);
            System.out.printf(movesBack, newScore, nextPlayer.getName(), playerScore);
        }
        player.setScore(newScore);
        playersPos.put(newScore, player);
    }
}
