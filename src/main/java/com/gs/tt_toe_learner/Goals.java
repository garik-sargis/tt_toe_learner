package com.gs.tt_toe_learner;

import com.gs.tt_toe_learner.data.GameResult;
import com.gs.tt_toe_learner.data.Symbol;
import com.gs.tt_toe_learner.utils.Boards;

/**
 * A collection of constants of type {@link Goal}.
 */
public final class Goals {
    private Goals() {
        // Do not instantiate
    }

    public static Goal WIN_X = winGoal(Symbol.X);

    public static Goal WIN_O = winGoal(Symbol.O);

    private static Goal winGoal(final Symbol symbol) {
        return worldState -> {
            final GameResult result = Boards.result(worldState.board());
            switch (result) {
                case DRAW:
                    return 0.0;
                case WIN_X:
                    return symbol == Symbol.X ? 1.0 : -1.0;
                case WIN_O:
                    return symbol == Symbol.O ? 1.0 : -1.0;
                default:
                    return 0.0;
            }
        };
    }
}
