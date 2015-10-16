package com.gs.tt_toe_learner.utils;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

import java.util.stream.Stream;

public final class Utils {

    public static Stream<Integer> intRange(final int lower, final int upper) {
        return ContiguousSet.create(Range.closedOpen(lower, upper), DiscreteDomain.integers()).stream();
    }
}
