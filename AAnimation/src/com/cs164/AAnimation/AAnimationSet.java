package com.cs164.AAnimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amytang on 12/8/14.
 */
public class AAnimationSet {
    private List<Animator> animators;
    private List<AAnimationState> states;
    private AnimatorSet godzillaSet;
    private boolean isRunning;


    public AAnimationSet(List<Animator> animators, List<AAnimationState> states){
        this.animators = animators;
        this.states = states;
        this.godzillaSet = new AnimatorSet();
        godzillaSet.playTogether(animators);
        godzillaSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isRunning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isRunning = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isRunning = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public AAnimationSet(List<AAnimationSet> animationSets) {
        animators = new ArrayList<Animator>();
        states = new ArrayList<AAnimationState>();
        for (AAnimationSet aAnimationSet: animationSets) {
            animators.addAll(aAnimationSet.getAnimators());
            states.addAll(aAnimationSet.getStates());
        }
        this.godzillaSet = new AnimatorSet();
        godzillaSet.playTogether(animators);
        godzillaSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isRunning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isRunning = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isRunning = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    public List<Animator> getAnimators() {
        return animators;
    }

    public List<AAnimationState> getStates() {
        return states;
    }

    public void addListener(Animator.AnimatorListener animatorListener) {
        godzillaSet.addListener(animatorListener);
    }

    /**
     * run() runs the sequence of animations.
     */
    public void run() {
        godzillaSet.start();
    }

    public boolean isRunning() {
        return isRunning;
    }
    /**
     * cancel() cancels the sequences of animations.
     * TODO: As of now, there could be some wonkiness with threads/timing.
     */
    public void cancel() {
        // Cancel all existing animations
        godzillaSet.cancel();

        AnimatorSet endTransition = new AnimatorSet();
        ArrayList<Animator> animators = new ArrayList<Animator>();
        // Create new transitional animations
        for (final AAnimationState oldState: states) {
            final View view = oldState.view;
            final AAnimationState currState = new AAnimationState(oldState.view);

            // If the x position changed
            if (currState.x != oldState.x) {
                ValueAnimator translateX = ValueAnimator.ofFloat(currState.x, oldState.x);
                translateX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        // As the square animates, make sure the object's values are also updated.
                        float value = (Float) animation.getAnimatedValue();
                        view.setX(value);
                    }
                });
                animators.add(translateX);
            }

            // If the y position changed
            if (currState.y != oldState.y) {
                ValueAnimator translateY = ValueAnimator.ofFloat(currState.y, oldState.y);
                translateY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        // As the square animates, make sure the object's values are also updated.
                        float value = (Float) animation.getAnimatedValue();
                        view.setY(value);
                    }
                });
                animators.add(translateY);
            }

            // If the alpha changed
            if (currState.alpha != oldState.alpha) {
                ObjectAnimator fade = ObjectAnimator.ofFloat(view, "alpha", currState.alpha, oldState.alpha);
                fade.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (Math.abs(currState.alpha - 0.001f) <= 0 && oldState.alpha>= 0) {
                            view.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (Math.abs(oldState.alpha - 0.001f) <= 0) {
                            view.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                animators.add(fade);
            }
            // If rotation changed
            if (currState.rotation != oldState.rotation) {
                ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", currState.rotation, oldState.rotation);
                animators.add(rotate);
            }

            // If scale changed
            if (currState.scale != oldState.scale) {
                ValueAnimator scaleAnimation = ValueAnimator.ofFloat(currState.scale, oldState.scale);
                scaleAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        // As the square animates, make sure the object's values are also updated.
                        float value = (Float) animation.getAnimatedValue();
                        view.setScaleX(value);
                        view.setScaleY(value);
                    }
                });
                animators.add(scaleAnimation);
            }
        }

        endTransition.playTogether(animators);
        endTransition.start();

    }
}
