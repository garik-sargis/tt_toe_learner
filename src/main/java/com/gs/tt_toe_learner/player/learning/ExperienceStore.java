package com.gs.tt_toe_learner.player.learning;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.gs.tt_toe_learner.Action;
import com.gs.tt_toe_learner.WorldState;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class ExperienceStore {

    private static final class Stat implements Comparable<Stat> {
        public static Stat EMPTY = new Stat(0.0, 0);

        public static Stat with(final double payoff) {
            return new Stat(payoff, 1);
        }

        private final double mAvgPayoff;

        private final int mGameCount;

        public Stat(final double avgPayoff, final int gameCount) {
            mAvgPayoff = avgPayoff;
            mGameCount = gameCount;
        }

        public double avgPayoff() {
            return mAvgPayoff;
        }

        public int gameCount() {
            return mGameCount;
        }

        @Override
        public int compareTo(final Stat that) {
            return Double.compare(this.mAvgPayoff, that.avgPayoff());
        }
    }

    private static Stat updatedStat(final Stat stat, final double payoff) {
        // Old Update Strategy
//        final int newGameCount = stat.gameCount() + 1;
//        final double newAvgPayoff = (stat.avgPayoff() * stat.gameCount() + payoff) / newGameCount;

        final int newGameCount = stat.gameCount() + 1;
        final double newAvgPayoff = stat.avgPayoff() + 0.5 * (payoff - stat.avgPayoff());
        return new Stat(newAvgPayoff, newGameCount);
    }

    public static ExperienceStore empty() {
        return new ExperienceStore();
    }

    private final Table<WorldState, Action, Stat> mTable;


    private ExperienceStore() {
        mTable = HashBasedTable.create();

    }

    public void insertExperience(final WorldState worldState,
                                 final Action action,
                                 final double payoff) {
        final Map<Action, Stat> row = mTable.row(worldState);
        final Stat oldStat = row.remove(action);
        final Stat newStat = updatedStat(oldStat, payoff);
        row.put(action, newStat);
    }

    public void insertPossibleActions(final WorldState worldState,
                                      final List<? extends Action> actions) {
        if (!mTable.containsRow(worldState)) {
            actions.forEach(action -> mTable.put(worldState, action, Stat.EMPTY));
        }
    }

    public Optional<Action> bestResponse(WorldState worldState) {
        final Optional<Action> optNonTestedAction = mTable.row(worldState)
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().gameCount() == 0)
                .map(Map.Entry::getKey)
                .findAny();

        final Optional<Action> optBestTestedAction = mTable.row(worldState)
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);

        return optNonTestedAction.isPresent() ? optNonTestedAction : optBestTestedAction;
    }
}
