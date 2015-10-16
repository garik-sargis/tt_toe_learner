package com.gs.tt_toe_learner.data;

import com.gs.tt_toe_learner.WorldState;

public final class TTToeState implements WorldState {
    private final Board mBoard;
    private final Symbol mTurn;

    public TTToeState(final Board board, final Symbol turn) {
        mBoard = board;
        mTurn = turn;
    }

    public Board board() {
        return mBoard;
    }

    public Symbol turn() {
        return mTurn;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TTToeState that = (TTToeState) o;

        return mBoard.equals(that.mBoard) && mTurn == that.mTurn;

    }

    @Override
    public int hashCode() {
        int result = mBoard.hashCode();
        result = 31 * result + mTurn.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return mBoard.toString();
    }
}
