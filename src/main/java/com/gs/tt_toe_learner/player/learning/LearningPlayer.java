package com.gs.tt_toe_learner.player.learning;

import com.gs.tt_toe_learner.Action;
import com.gs.tt_toe_learner.WorldState;
import com.gs.tt_toe_learner.player.GoalPlayer;

import java.util.ArrayList;
import java.util.List;

public final class LearningPlayer implements GoalPlayer {

    private static final class Decision {
        private final WorldState mWorldState;
        private final Action mAction;

        public Decision(final WorldState worldState, final Action action) {
            mWorldState = worldState;
            mAction = action;
        }

        public WorldState worldState() {
            return mWorldState;
        }

        public Action action() {
            return mAction;
        }
    }

    private final ExperienceStore mExperienceStore;

    private final List<Decision> mDecisions;

    public LearningPlayer() {
        mDecisions = new ArrayList<>();
        mExperienceStore = ExperienceStore.empty();
    }

    @Override
    public void starting() {

    }

    @Override
    public Action play(final WorldState worldState, final List<? extends Action> actions) {
        mExperienceStore.insertPossibleActions(worldState, actions);
        final Action someAction = actions.stream().findAny().get();
        final Action action = mExperienceStore.bestResponse(worldState).orElse(someAction);
        mDecisions.add(new Decision(worldState, action));
        return action;
    }

    @Override
    public void finished(final double payoff) {
        mDecisions.forEach(decision -> {
            mExperienceStore.insertExperience(decision.worldState(), decision.action(), payoff);
        });
        mDecisions.clear();
    }
}
