package com.cs164.AAnimation;

/**
 * Holds the values for initial animation state.
 */
public class AnimationState {
    public int x, y;
    public int rotation;
    public float alpha;

    public AnimationState(int x, int y, int rotation, float alpha) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.alpha = alpha;
    }

    public AnimationState(int x, int y) {
        this(x, y, 0, 1.0f);
    }

}
