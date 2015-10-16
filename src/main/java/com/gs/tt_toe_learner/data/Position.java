package com.gs.tt_toe_learner.data;

import com.gs.tt_toe_learner.Action;

/**
 * A value-class representing positions of a Tic-Tac-Toe board.
 */
public final class Position implements Action {

    public static Position with(int row, int col) {
        return new Position(row, col);
    }

    private final int mRow;
    private final int mCol;

    private Position(int row, int col) {
        mRow = row;
        mCol = col;
    }

    public int row() {
        return mRow;
    }

    public int col() {
        return mCol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return mRow == position.mRow && mCol == position.mCol;

    }

    @Override
    public int hashCode() {
        int result = mRow;
        result = 31 * result + mCol;
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", mRow, mCol);
    }
}
