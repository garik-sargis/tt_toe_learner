package com.gs.tt_toe_learner.player;

import com.gs.tt_toe_learner.Action;
import com.gs.tt_toe_learner.WorldState;

import java.util.List;

public final class LoggingPlayer implements Player {

    public static LoggingPlayer withName(final String name, final Player wrappedPlayer) {
        return new LoggingPlayer(name, wrappedPlayer);
    }

    private final String mName;
    private final Player mWrappedPlayer;

    private LoggingPlayer(final String name, final Player wrappedPlayer) {
        mName = name;
        mWrappedPlayer = wrappedPlayer;
    }

    @Override
    public void starting() {
        System.out.println(String.format("%s: Game about to start", mName));
        mWrappedPlayer.starting();
    }

    @Override
    public Action play(final WorldState worldState, final List<? extends Action> actions) {
        return mWrappedPlayer.play(worldState, actions);
    }

    @Override
    public void finished(final WorldState finalWorldState) {
        System.out.println(String.format("%s: Game finished", mName));
        mWrappedPlayer.finished(finalWorldState);
    }
}
