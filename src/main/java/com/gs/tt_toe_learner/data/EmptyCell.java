package com.gs.tt_toe_learner.data;

public enum EmptyCell implements CellState {
    EMPTY;

    @Override
    public String toString() {
        return ".";
    }
}
