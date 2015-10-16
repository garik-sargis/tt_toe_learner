package com.gs.tt_toe_learner.stat;

import com.gs.tt_toe_learner.TTToeCoordinator;
import com.gs.tt_toe_learner.data.GameResult;
import com.gs.tt_toe_learner.player.Player;

import java.util.EnumMap;
import java.util.Map;

public final class TTToeStats {
    public static Map<GameResult, Integer> getStats(Player playerX, Player playerO, int times) {
        EnumMap<GameResult, Integer> statMap = new EnumMap<>(GameResult.class);
        for (final GameResult result : GameResult.values()) {
            statMap.put(result, 0);
        }

        final TTToeCoordinator coordinator = new TTToeCoordinator(playerX, playerO);
        for (int i = 0; i < times; i++) {
            final GameResult result = coordinator.start();
            final int oldCount = statMap.get(result);
            statMap.put(result, oldCount + 1);
        }

        return statMap;
    }

    public static void printStats(Player playerX, Player playerO, int times) {
        final Map<GameResult, Integer> statsMap = getStats(playerX, playerO, times);
        System.out.println(statsMap);
    }
}
