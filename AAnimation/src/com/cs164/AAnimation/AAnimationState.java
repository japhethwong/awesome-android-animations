package com.cs164.AAnimation;

import android.view.View;

/**
 * Holds the values for initial animation state.
 */
public class AAnimationState {
    public View view;
    public float x, y;
    public float rotation;
    public float alpha;
    public float scale;

    public AAnimationState(View view) {
        this.view = view;
        x = view.getX();
        y = view.getY();
        rotation = view.getRotation();
        alpha = view.getAlpha();
        scale = view.getScaleX();
    }

}
