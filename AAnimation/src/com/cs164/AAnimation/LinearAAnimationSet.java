package com.cs164.AAnimation;

import android.animation.Animator;

import java.util.List;

/**
 * Created by milkyway on 12/10/14.
 */
public class LinearAAnimationSet extends AAnimationSet {

    public LinearAAnimationSet(List<Animator> animators, List<AAnimationState> states) {
        super(animators, states);
        godzillaSet.playSequentially(animators);
    }

    public LinearAAnimationSet(List < AAnimationSet > animationSets) {
        super(animationSets);
        godzillaSet.playSequentially(animators);
    }
}
