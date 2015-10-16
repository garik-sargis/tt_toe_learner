package com.gs.tt_toe_learner.player;

import com.gs.tt_toe_learner.Action;
import com.gs.tt_toe_learner.WorldState;

import java.util.List;
import java.util.Random;

public final class RandomPlayer implements Player {

    public static RandomPlayer withSeed(final long seed) {
        return new RandomPlayer(new Random(seed));
    }

    private final Random mRandom;

    private RandomPlayer(final Random random) {
        mRandom = random;
    }

    @Override
    public Action play(final WorldState worldState, final List<? extends Action> actions) {
        final int index = mRandom.nextInt(actions.size());
        return actions.get(index);
    }
}
