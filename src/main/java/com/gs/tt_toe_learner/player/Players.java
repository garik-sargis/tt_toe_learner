package com.gs.tt_toe_learner.player;

import com.gs.tt_toe_learner.Action;
import com.gs.tt_toe_learner.Goal;
import com.gs.tt_toe_learner.WorldState;
import com.gs.tt_toe_learner.data.TTToeState;

import java.util.List;

/**
 * Factory methods for the {@link Player} interface.
 */
public final class Players {
    public static Player withGoal(final GoalPlayer gPlayer, final Goal goal) {
        return new Player() {

            @Override
            public void starting() {
                gPlayer.starting();
            }

            @Override
            public Action play(final WorldState worldState, final List<? extends Action> actions) {
                return gPlayer.play(worldState, actions);
            }

            @Override
            public void finished(final WorldState finalWorldState) {
                gPlayer.finished(goal.payoff((TTToeState) finalWorldState));
            }
        };
    }

    public static Player random(final long seed) {
        return RandomPlayer.withSeed(seed);
    }

    public static Player logging(final String name, Player p) {
        return LoggingPlayer.withName(name, p);
    }

    public static Player console() {
        return ConsolePlayer.create();
    }
}
