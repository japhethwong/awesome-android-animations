package com.cs164.AAnimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amytang on 12/8/14.
 */
public class TranslateAAnimationFactory extends BasicAAnimationFactory {
    Float startX, startY;
    float endX, endY;

    /*
    public TranslateAAnimationFactory(Float startX, Float startY, float endX, float endY, int duration, int wait) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.duration = duration;
        this.wait = wait;
    }

    public TranslateAAnimationFactory(Float startX, Float startY, float endX, float endY) {
        this(startX, startY, endX, endY, 200, 0);
    }
    */

    public TranslateAAnimationFactory(float endX, float endY, int duration, int wait) {
        this.endX = endX;
        this.endY = endY;
        this.duration = duration;
        this.wait = wait;
    }

    public TranslateAAnimationFactory(float endX, float endY) {
       // this(null, null, endX, endY, 200, 0);
        this(endX, endY, 200, 0);
    }
    @Override
    public AAnimationSet apply(List<View> objects) {
        ArrayList<Animator> animators = new ArrayList<Animator>();
        ArrayList<AAnimationState> states = new ArrayList<AAnimationState>();
        for (final View view: objects) {
            AAnimationState state = new AAnimationState(view);
            states.add(state);

            float x1, y1, x2, y2;

//            if (startX == null && startY == null) {
                // If startX, startY are null, take endX and endY as distances, not exact coordinates
                x1 = state.x;
                y1 = state.y;
                x2 = state.x + endX;
                y2 = state.y + endY;
//            } else {
                // Otherwise if startX and startY are provided
//                x1 = startX;
//                y1 = startY;
//                x2 = endX;
//                y2 = endY;
//            }
            AnimatorSet translateXY = new AnimatorSet();
            ValueAnimator translateX = ValueAnimator.ofFloat(x1, x2);
            translateX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // As the square animates, make sure the object's values are also updated.
                    float value = (Float) animation.getAnimatedValue();
                    view.setX(value);
                    // Incompatible with Gingerbread
                }
            });
            translateX.setDuration(duration);
            translateX.setStartDelay(wait);
            ValueAnimator translateY = ValueAnimator.ofFloat(y1, y2);
            translateY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // As the square animates, make sure the object's values are also updated.
                    float value = (Float) animation.getAnimatedValue();
                    view.setY(value);
                    // Incompatible with Gingerbread
                }
            });
            translateY.setDuration(duration);
            translateY.setStartDelay(wait);
            translateXY.playTogether(translateX, translateY);
            animators.add(translateXY);
        }
        return new ParallelAAnimationSet(animators, states);
    }
}