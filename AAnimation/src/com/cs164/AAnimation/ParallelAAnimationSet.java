package com.cs164.AAnimation;

import android.animation.Animator;

import java.util.List;

/**
 * Created by milkyway on 12/10/14.
 */
public class ParallelAAnimationSet extends AAnimationSet {

    public ParallelAAnimationSet(List<Animator> animators, List<AAnimationState> states) {
        super(animators, states);
        godzillaSet.playTogether(animators);
    }

    public ParallelAAnimationSet(List<AAnimationSet> animationSets) {
        super(animationSets);
        godzillaSet.playTogether(animators);
    }
}