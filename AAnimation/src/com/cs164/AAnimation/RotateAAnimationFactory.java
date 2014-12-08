package com.cs164.AAnimation;

import android.animation.Animator;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by amytang on 12/8/14.
 */
public class RotateAAnimationFactory extends BasicAAnimationFactory {
    private int rotation;

    public  RotateAAnimationFactory(int rotation, int duration, int wait) {
        this.rotation = rotation;
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


        }
        return new ParallelAAnimationSet(animators, states);
    }
}
