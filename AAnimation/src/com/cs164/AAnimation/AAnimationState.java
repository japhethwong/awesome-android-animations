package com.cs164.AAnimation;

import android.view.View;

/**
 * Holds the values for initial animation state.
 */
public class AAnimationState {
    public float x, y;
    public float rotation;
    public float alpha;
    public float scale;

    public AAnimationState(float x, float y, float rotation, float alpha, float scale) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.alpha = alpha;
        this.scale = scale;
    }

    public AAnimationState(float x, float y) {
        this(x, y, 0, 1.0f, 1.0f);
    }

    public AAnimationState(View view) {
        x = view.getX();
        y = view.getY();
        rotation = view.getRotation();
        alpha = view.getAlpha();
        scale = view.getScaleX();
    }

}
