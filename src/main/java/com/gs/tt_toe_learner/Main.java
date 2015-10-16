package com.gs.tt_toe_learner;

import com.gs.tt_toe_learner.player.Player;
import com.gs.tt_toe_learner.player.Players;
import com.gs.tt_toe_learner.player.learning.LearningPlayer;
import com.gs.tt_toe_learner.stat.TTToeStats;

public final class Main {

    public static void main(String[] args) {
        int trainCount;
        int gameCount;

        if (args.length != 2) {
            trainCount = 5000;
            gameCount = 10;
        } else {
            trainCount = Integer.parseInt(args[0]);
            gameCount = Integer.parseInt(args[1]);
        }

            final long seed = System.currentTimeMillis();

            final Player consolePlayer = Players.console();
            final Player learningPlayer = Players.withGoal(new LearningPlayer(), Goals.WIN_X);

            TTToeStats.getStats(learningPlayer, Players.random(seed), trainCount);

            TTToeStats.printStats(learningPlayer, consolePlayer, gameCount);
        }


}
