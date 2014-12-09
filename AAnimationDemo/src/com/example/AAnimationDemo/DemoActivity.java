package com.example.AAnimationDemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.cs164.AAnimation.*;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends Activity {
    private final static int TIME = 500;
    int count = 0;
    View square1, square2, square3;
    Button button;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        square1 = findViewById(R.id.square1);
        square2 = findViewById(R.id.square2);
        square3 = findViewById(R.id.square3);
        button = (Button)findViewById(R.id.button);

    }

    /**
     * Response handler to react to taps on our button which toggles animations.
     * @param v is the view which registered the tap
     */
    public void onClick(View v) {
        Log.d("HEY", "got clicked "+count);
        count++;

  //      runBasic();
        runWithFactory();
    }

    private void runBasic() {
        ArrayList<View> squares = new ArrayList<View>();
        squares.add(square1);
        squares.add(square2);
        squares.add(square3);

        FadeAAnimationFactory fade = new FadeAAnimationFactory(0,1,TIME,100);
        AAnimationSet fadeAnimations = fade.apply(squares);
        fadeAnimations.run();

        TranslateAAnimationFactory translate = new TranslateAAnimationFactory(30, 5, TIME, 100);
        AAnimationSet translateAnimations = translate.apply(squares);
        translateAnimations.run();

        RotateAAnimationFactory rotate = new RotateAAnimationFactory(-700, TIME, 100);
        AAnimationSet rotateAnimations = rotate.apply(squares);
        rotateAnimations.run();

        ScaleAAnimationFactory scale = new ScaleAAnimationFactory(0.9f, TIME, 100);
        AAnimationSet scaleAnimations = scale.apply(squares);
        scaleAnimations.run();
    }

    private void runWithFactory() {
        ArrayList<View> squares = new ArrayList<View>();
        squares.add(square1);
        squares.add(square2);
        squares.add(square3);

        FadeAAnimationFactory fade = new FadeAAnimationFactory(0,1,TIME,100);
        TranslateAAnimationFactory translate = new TranslateAAnimationFactory(30, 5, TIME, 100);
        RotateAAnimationFactory rotate = new RotateAAnimationFactory(-700, TIME, 100);
        ScaleAAnimationFactory scale = new ScaleAAnimationFactory(0.9f, TIME, 100);

        List<AAnimationFactory> animations = new ArrayList<AAnimationFactory>();
        animations.add(fade);
        animations.add(translate);
        animations.add(rotate);
        animations.add(scale);

        LinearAAnimationSetFactory linearAnimation = new LinearAAnimationSetFactory(animations);
        AAnimationSet set = linearAnimation.apply(squares);
        set.run();
    }

}
