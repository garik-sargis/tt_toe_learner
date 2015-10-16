package com.gs.tt_toe_learner.player;

import com.gs.tt_toe_learner.Action;
import com.gs.tt_toe_learner.WorldState;

import java.util.List;

public interface GoalPlayer {
    default void starting() {
        // Empty
    }

    Action play(WorldState worldState, List<? extends Action> actions);

    void finished(double payoff);
}
