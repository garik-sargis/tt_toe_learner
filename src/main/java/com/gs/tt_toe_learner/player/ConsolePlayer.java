package com.gs.tt_toe_learner.player;

import com.google.common.collect.ImmutableList;
import com.gs.tt_toe_learner.Action;
import com.gs.tt_toe_learner.WorldState;
import com.gs.tt_toe_learner.data.Board;
import com.gs.tt_toe_learner.data.Position;

import java.util.List;
import java.util.Scanner;

public final class ConsolePlayer implements Player {

    public static ConsolePlayer create() {
        return new ConsolePlayer();
    }

    private static final ImmutableList<String> rowIndexes = Board.rowIndexes;

    private static final ImmutableList<String> colIndexes = ImmutableList.of("1", "2", "3");

    private final Scanner mScanner;

    private ConsolePlayer() {
        mScanner = new Scanner(System.in);
    }

    @Override
    public void starting() {
        System.out.println("Game Starting");
    }

    @Override
    public Action play(final WorldState worldState, final List<? extends Action> actions) {
        System.out.println("\n" + worldState);

        while (true) {
            final Position position = readInput();
            if (actions.contains(position)) {
                return position;
            } else {
                System.out.println("Illegal input");
            }
        }
    }

    private Position readInput() {
        while (true) {
            System.out.print("Move: ");

            final String input = mScanner.nextLine();
            if (input.length() < 2) break;

            final String c0 = Character.toString(input.charAt(0)).toUpperCase();
            final String c1 = Character.toString(input.charAt(1));

            if (rowIndexes.contains(c0) && colIndexes.contains(c1)) {
                final int row = rowIndexes.indexOf(c0);
                final int col = colIndexes.indexOf(c1);
                return Position.with(row, col);
            }
        }

        return null;
    }

    @Override
    public void finished(final WorldState finalWorldState) {
        System.out.println("Game Finished");
    }
}
