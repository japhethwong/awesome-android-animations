package com.cs164.AAnimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Japheth on 12/9/2014.
 */
public class LinearAAnimationSetFactory extends AAnimationSetFactory {
    public LinearAAnimationSetFactory(List<AAnimationFactory> animations) {
        super(animations);
    }

    @Override
    public AAnimationSet apply(List<View> objects) {
        AnimatorSet godzillaSet = new AnimatorSet();
        List<Animator> animators = new ArrayList<Animator>();
        List<AAnimationState> states = new ArrayList<AAnimationState>();

        // Build the list of states needed to construct an AAnimationSet.
        for (final View v : objects) {
            states.add(new AAnimationState(v));
        }

        // Create an AnimatorSet which sequentially plays each of the animations in factory.
        for (final AAnimationFactory animation : animations) {
            AAnimationSet animationSet = animation.apply(objects);
            if (animation instanceof TranslateAAnimationFactory) {
                Log.d("HEY", "animationSet animators: " + animationSet.getAnimators());
            }
            AnimatorSet set = new AnimatorSet();
            List<Animator> currentAnimators = new ArrayList<Animator>();

            for (Animator a: animationSet.getAnimators()) {
                currentAnimators.add(a);
            }

            set.playTogether(currentAnimators);
            animators.add(set);
        }

        godzillaSet.playSequentially(animators);
        List<Animator> toReturn = new ArrayList<Animator>();
        toReturn.add(godzillaSet);
        return new LinearAAnimationSet(toReturn, states);
    }
}
