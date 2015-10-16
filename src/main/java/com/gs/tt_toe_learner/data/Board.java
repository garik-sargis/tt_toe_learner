package com.gs.tt_toe_learner.data;

import com.google.common.collect.ImmutableList;
import com.gs.tt_toe_learner.utils.Utils;

import java.util.Arrays;

/**
 * A value-class representing states of a Tic-Tac-Toe board.
 */
public final class Board {

    public static final int ROW_COUNT = 3;
    public static final int COL_COUNT = 3;

    public static final Board EMPTY = genEmpty();

    private static Board genEmpty() {
        CellState[][] rows = new CellState[3][3];
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                rows[row][col] = CellState.EMPTY;
            }
        }

        return new Board(rows);
    }

    private final CellState[][] mRows;

    private Board(final CellState[][] rows) {
        mRows = rows;
    }

    public CellState get(final int row, final int col) {
        return mRows[row][col];
    }

    public CellState get(Position p) {
        return mRows[p.row()][p.col()];
    }

    public Board changeCell(final int row, final int col, final CellState val) {
        final CellState[][] newRows = new CellState[ROW_COUNT][];

        Utils.intRange(0, ROW_COUNT)
                .filter(index -> index != row)
                .forEach(index -> newRows[index] = mRows[index]);

        newRows[row] = new CellState[COL_COUNT];
        System.arraycopy(mRows[row], 0, newRows[row], 0, COL_COUNT);
        newRows[row][col] = val;

        return new Board(newRows);
    }

    public Board changeCell(final Position pos, final CellState val) {
        return changeCell(pos.row(), pos.col(), val);
    }

    public static final ImmutableList<String> rowIndexes = ImmutableList.of("C", "B", "A");

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int r = 0; r < Board.ROW_COUNT; r++) {
            builder.append(rowIndexes.get(r)).append(" ");
            for (int c = 0; c < Board.COL_COUNT; c++) {
                final CellState cell = get(r, c);
                builder.append(cell.toString()).append(" ");
            }
            builder.append("\n");
        }

        builder.append("  1 2 3\n");

        return builder.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Board board = (Board) o;

        return Arrays.deepEquals(mRows, board.mRows);

    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(mRows);
    }
}
