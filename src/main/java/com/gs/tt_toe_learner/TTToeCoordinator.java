package com.gs.tt_toe_learner;

import com.gs.tt_toe_learner.data.*;
import com.gs.tt_toe_learner.player.Player;
import com.gs.tt_toe_learner.utils.Boards;

public class TTToeCoordinator {

    private static final TTToeState INIT_STATE = new TTToeState(Board.EMPTY, Symbol.X);

    private final Player mPlayerX;
    private final Player mPlayerO;

    private final TTToeRules mWorldRules;

    private TTToeState mWorldState;

    public TTToeCoordinator(final Player playerX,
                            final Player playerO) {
        mPlayerX = playerX;
        mPlayerO = playerO;

        mWorldRules = new TTToeRules();
    }

    private void updateWorldState(TTToeState newState) {
        mWorldState = newState;
    }

    private Player currentPlayer() {
        switch (mWorldState.turn()) {
            case X:
                return mPlayerX;
            case O:
                return mPlayerO;
            default:
                throw new IllegalStateException();
        }
    }

    private void step() {
        final Player player = currentPlayer();
        final Position action = (Position) player.play(mWorldState, mWorldRules.actions(mWorldState));
        updateWorldState(mWorldRules.next(mWorldState, action));
    }

    private void notifyStarting() {
        mPlayerX.starting();
        mPlayerO.starting();
    }

    private void notifyFinished() {
        mPlayerX.finished(mWorldState);
        mPlayerO.finished(mWorldState);
    }

    public GameResult start() {
        updateWorldState(INIT_STATE);
        notifyStarting();
        while (!mWorldRules.isFinal(mWorldState)) {
            step();
        }
        notifyFinished();
        return Boards.result(mWorldState.board());
    }
}
