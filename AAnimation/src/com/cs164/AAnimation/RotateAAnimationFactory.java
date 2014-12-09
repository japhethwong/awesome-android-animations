package com.cs164.AAnimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amytang on 12/8/14.
 */
public class RotateAAnimationFactory extends BasicAAnimationFactory {
    private int rotation;

    /**
     *
     * @param rotation is an offset in degrees. Negative for counter-clockwise.
     * @param duration
     * @param wait
     */
    public  RotateAAnimationFactory(int rotation, int duration, int wait) {
        this.rotation = rotation;
        this.duration = duration;
        this.wait = wait;
    }

    public RotateAAnimationFactory(int rotation) {
        this(rotation, 200, 0);
    }
    @Override
    public AAnimationSet apply(List<View> objects) {
        ArrayList<Animator> animators = new ArrayList<Animator>();
        ArrayList<AAnimationState> states = new ArrayList<AAnimationState>();
        for (final View view: objects) {
            AAnimationState state = new AAnimationState(view);
            states.add(state);

            ObjectAnimator rotate = ObjectAnimator.ofFloat(
                    view,
                    "rotation",
                    view.getRotation(),
                    view.getRotation()+rotation
            );
            rotate.setDuration(duration);
            animators.add(rotate);
        }
        return new AAnimationSet(animators, states);
    }
}
