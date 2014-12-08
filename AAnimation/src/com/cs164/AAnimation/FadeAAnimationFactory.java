package com.cs164.AAnimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by amytang on 12/8/14.
 */
public class FadeAAnimationFactory extends BasicAAnimationFactory {
    float startAlpha, endAlpha;
    int duration;
    int wait;

    public FadeAAnimationFactory(float startAlpha, float endAlpha, int duration, int wait) {
        this.startAlpha = startAlpha;
        this.endAlpha = endAlpha;
        this.duration = duration;
        this.wait = wait;
    }

    public FadeAAnimationFactory(float startAlpha, float endAlpha) {
        this(startAlpha, endAlpha, 200, 0);
    }
    @Override
    public AAnimationSet apply(ArrayList<View> objects) {
        ArrayList<Animator> animators = new ArrayList<Animator>();
        ArrayList<AAnimationState> states = new ArrayList<AAnimationState>();
        for (final View view: objects) {
            AAnimationState state = new AAnimationState(view);
            states.add(state);
            ObjectAnimator fade = ObjectAnimator.ofFloat(view, "alpha", startAlpha, endAlpha);
            fade.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (Math.abs(startAlpha - 0.001f) <= 0 && endAlpha >= 0) {
                        view.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (Math.abs(endAlpha - 0.001f) <= 0) {
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
        return new ParallelAAnimationSet(animators, states);
    }
}
