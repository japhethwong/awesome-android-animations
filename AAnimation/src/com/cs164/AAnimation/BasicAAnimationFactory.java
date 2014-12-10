package com.cs164.AAnimation;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amytang on 12/8/14.
 */
public class BasicAAnimationFactory implements AAnimationFactory {
    int duration;
    int wait;
    @Override
    public AAnimationSet apply(List<View> objects) {
        return null;
    }

    @Override
    public AAnimationSet apply(View object) {
        ArrayList<View> objects = new ArrayList<View>();
        objects.add(object);
        return this.apply(objects);
    }

}
