package com.gs.tt_toe_learner;

import com.gs.tt_toe_learner.data.*;
import com.gs.tt_toe_learner.utils.Boards;

import java.util.List;
import java.util.stream.Collectors;

public final class TTToeRules {

    private static Symbol otherSymbol(Symbol symbol) {
        switch (symbol) {
            case X:
                return Symbol.O;
            case O:
                return Symbol.X;
            default:
                throw new IllegalArgumentException();
        }
    }

    public List<Position> actions(final TTToeState worldState) {
        return Boards.emptyPositions(worldState.board()).collect(Collectors.toList());
    }

    public TTToeState next(final TTToeState worldState, final Position action) {
        final Board oldBoard = worldState.board();
        final Symbol oldTurn = worldState.turn();
        final Board newBoard = oldBoard.changeCell(action, oldTurn);
        final Symbol newTurn = otherSymbol(oldTurn);
        return new TTToeState(newBoard, newTurn);
    }


    public boolean isFinal(final TTToeState worldState) {
        final Board board = worldState.board();
        final GameResult result = Boards.result(board);
        return result != GameResult.NONE;
    }
}
