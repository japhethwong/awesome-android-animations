package com.cs164.AAnimation;

import android.animation.Animator;
import android.view.View;

import java.util.List;

/**
 * Created by Japheth on 12/8/2014.
 */
public class ParallelAAnimationSetFactory extends AAnimationSetFactory {
    public ParallelAAnimationSetFactory(List<AAnimationFactory> animations) {
        super(animations);
    }

    @Override
    public AAnimationSet apply(List<View> objects) {
        // TODO: Implement me!
        return super.apply(objects);
    }
}
