package com.cs164.AAnimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by amytang on 12/8/14.
 */
public interface AAnimationSet {

    /**
     * run() runs the sequence of animations.
     */
    public void run();

    /**
     * cancel() cancels the sequences of animations.
     */
    public void cancel();
}
