package com.gs.tt_toe_learner;

import com.gs.tt_toe_learner.data.TTToeState;

public interface Goal {
    double payoff(TTToeState worldState);
}
