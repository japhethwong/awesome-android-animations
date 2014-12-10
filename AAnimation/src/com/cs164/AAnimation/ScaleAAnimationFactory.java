package com.cs164.AAnimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * This scales proportionally in both x and y.
 */
public class ScaleAAnimationFactory extends BasicAAnimationFactory {
    float scale;

    public ScaleAAnimationFactory(float scale, int duration, int wait) {
        this.scale = scale;
        this.duration = duration;
        this.wait = wait;
    }

    public ScaleAAnimationFactory(float scale) {
        this(scale, 200, 0);
    }

    @Override
    public AAnimationSet apply(List<View> objects) {
        ArrayList<Animator> animators = new ArrayList<Animator>();
        ArrayList<AAnimationState> states = new ArrayList<AAnimationState>();
        for (final View view: objects) {
            AAnimationState state = new AAnimationState(view);
            states.add(state);

            ValueAnimator scaleAnimation = ValueAnimator.ofFloat(view.getScaleX(), scale*view.getScaleX());
            scaleAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // As the square animates, make sure the object's values are also updated.
                    float value = (Float) animation.getAnimatedValue();
                    view.setScaleX(value);
                    view.setScaleY(value);
                }
            });
            scaleAnimation.setDuration(duration);
            scaleAnimation.setStartDelay(wait);
            animators.add(scaleAnimation);
        }
        return new ParallelAAnimationSet(animators, states);
    }
}
