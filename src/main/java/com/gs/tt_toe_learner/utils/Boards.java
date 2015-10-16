package com.gs.tt_toe_learner.utils;

import com.gs.tt_toe_learner.data.*;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/**
 * Utility methods for {@link Board}
 */
public final class Boards {
    private Boards() {
        // Do not instantiate
    }

    public static GameResult result(Board board) {
        if (lines(board).anyMatch(line -> line.allMatch(Predicate.isEqual(Symbol.X)))) {
            return GameResult.WIN_X;
        } else if (lines(board).anyMatch(line -> line.allMatch(Predicate.isEqual(Symbol.O)))) {
            return GameResult.WIN_O;
        } else if (isFull(board)) {
            return GameResult.DRAW;
        } else {
            return GameResult.NONE;
        }
    }

    public static Stream<Integer> rowIndexes() {
        return Utils.intRange(0, Board.ROW_COUNT);
    }

    public static Stream<Integer> colIndexes() {
        return Utils.intRange(0, Board.COL_COUNT);
    }

    public static Stream<CellState> row(final Board board, final int rowIndex) {
        return colIndexes().map(columnIndex -> board.get(rowIndex, columnIndex));
    }

    public static Stream<Stream<CellState>> rows(final Board board) {
        return rowIndexes().map(rowIndex -> row(board, rowIndex));
    }

    public static Stream<CellState> col(final Board board, final int colIndex) {
        return rowIndexes().map(rowIndex -> board.get(rowIndex, colIndex));
    }

    public static Stream<Stream<CellState>> cols(final Board board) {
        return colIndexes().map(colIndex -> col(board, colIndex));
    }

    public static Stream<CellState> diag1(Board board) {
        return rowIndexes().map(index -> board.get(index, index));
    }

    public static Stream<CellState> diag2(Board board) {
        return rowIndexes().map(index -> board.get(index, Board.COL_COUNT - 1 - index));
    }

    public static Stream<Stream<CellState>> lines(final Board board) {
        return concat(concat(concat(rows(board), cols(board)), Stream.of(diag1(board))), Stream.of(diag2(board)));
    }

    public static Stream<CellState> cells(final Board board) {
        return rowIndexes().flatMap(rowIndex -> row(board, rowIndex));
    }

    public static boolean isFull(final Board board) {
        return cells(board).noneMatch(Predicate.isEqual(CellState.EMPTY));
    }

    public static Stream<Position> positions() {
        return rowIndexes().flatMap(rowIndex -> colIndexes().map(colIndex -> Position.with(rowIndex, colIndex)));
    }

    public static Stream<Position> emptyPositions(final Board board) {
        return positions().filter(pos -> board.get(pos) == CellState.EMPTY);
    }
}
