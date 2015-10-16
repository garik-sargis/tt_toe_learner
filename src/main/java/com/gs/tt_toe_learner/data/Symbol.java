package com.gs.tt_toe_learner.data;

public enum Symbol implements CellState {
    X, O;

    public Symbol opposite() {
        switch (this) {
            case X:
                return O;
            case O:
                return X;
            default:
                throw new IllegalStateException();
        }
    }
}
