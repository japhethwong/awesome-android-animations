package com.cs164.AAnimation;

import android.view.View;

/**
 * Holds the values for initial animation state.
 */
public class AAnimationState {
    public final View view;
    public final float x, y;
    public final float rotation;
    public final float alpha;
    public final float scale;

    public AAnimationState(View view) {
        this.view = view;
        x = view.getX();
        y = view.getY();
        rotation = view.getRotation();
        alpha = view.getAlpha();
        scale = view.getScaleX();
    }

}
