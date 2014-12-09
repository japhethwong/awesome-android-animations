package com.cs164.AAnimation;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amytang on 12/8/14.
 */
public interface AAnimationFactory {
    /**
     * apply() binds a list of targets to this animation factory.
     * @param objects is the list of targets to apply this animation factory to
     * @return an AAnimationSet which can be run or canceled
     */
    public AAnimationSet apply(List<View> objects);
}

