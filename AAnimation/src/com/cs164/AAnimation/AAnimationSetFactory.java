package com.cs164.AAnimation;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milkyway on 12/8/14.
 */
public class AAnimationSetFactory implements AAnimationFactory {
    protected boolean shouldCancel;
    protected List<AAnimationFactory> animations;

    public AAnimationSetFactory(List<AAnimationFactory> animations) {
        this.shouldCancel = true;   // Default behavior: enable cancel animations.
        this.animations = animations;
    }

    @Override
    public AAnimationSet apply(List<View> objects) {
        assert false : "Subclasses of AAnimationSetFactory are responsible for implementing an apply() method.";
        return null;
    }

    @Override
    public AAnimationSet apply(View object) {
        ArrayList<View> objects = new ArrayList<View>();
        objects.add(object);
        return this.apply(objects);
    }
    /**
     * setCancel allows the user to toggle the feature for enabling/disabling our cancel animation sequence.
     * @param shouldCancel is true if cancel animation should be enabled, false otherwise (use default behavior for
     *                     canceling an animation)
     */
    public void setCancel(boolean shouldCancel) {
        this.shouldCancel = shouldCancel;
    }

    /**
     * getCancel() retrieves the cancel setting for our cancel animation sequence.  Only the cancel setting for the
     * outermost animation set layer is used to determine whether or not to apply the animation.
     * @return shouldCancel, a boolean which determines whether the cancel animation sequence is enabled (true) or
     * disabled (false)
     */
    public boolean getCancel() {
        return shouldCancel;
    }
}
