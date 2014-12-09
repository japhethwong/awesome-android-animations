package com.cs164.AAnimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by amytang on 12/8/14.
 */
public class RotateAAnimationFactory extends BasicAAnimationFactory {
    private int rotation;

    public  RotateAAnimationFactory(int rotation, int duration, int wait) {
        this.rotation = rotation%360 >= 0 ? rotation%360 : 360 - rotation%360;
        this.duration = duration;
        this.wait = wait;
    }

    public RotateAAnimationFactory(int rotation) {
        this(rotation, 200, 0);
    }
    @Override
    public AAnimationSet apply(ArrayList<View> objects) {
        ArrayList<Animator> animators = new ArrayList<Animator>();
        ArrayList<AAnimationState> states = new ArrayList<AAnimationState>();
        for (final View view: objects) {
            AAnimationState state = new AAnimationState(view);
            states.add(state);

            ObjectAnimator fade = ObjectAnimator.ofFloat(view, "rotation", 0f, rotation);
            animators.add(fade);
        }
        return new ParallelAAnimationSet(animators, states);
    }
}
