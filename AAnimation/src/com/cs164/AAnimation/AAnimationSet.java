package com.cs164.AAnimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amytang on 12/8/14.
 */
public class AAnimationSet {
    List<Animator> animators;
    List<AAnimationState> states;
    boolean isParallel;

    public AAnimationSet(List<Animator> animators, List<AAnimationState> states){
        this.animators = animators;
        this.states = states;
    }

    /**
     * run() runs the sequence of animations.
     */
    public void run() {
        for (final Animator animator : animators) {
            animator.start();
        }
    }

    /**
     * cancel() cancels the sequences of animations.
     */
    public void cancel() {

    }
}
