package com.cs164.AAnimation;

/**
 * Holds the values for initial animation state.
 */
public class AAnimationState {
    public int x, y;
    public int rotation;
    public float alpha;
    public float scale;

    public AAnimationState(int x, int y, int rotation, float alpha, float scale) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.alpha = alpha;
        this.scale = scale;
    }

    public AAnimationState(int x, int y) {
        this(x, y, 0, 1.0f, 1.0f);
    }

}
