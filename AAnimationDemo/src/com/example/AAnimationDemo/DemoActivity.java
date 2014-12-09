package com.example.AAnimationDemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.cs164.AAnimation.*;

import java.util.ArrayList;

public class DemoActivity extends Activity {
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
        FadeAAnimationFactory fade = new FadeAAnimationFactory(0,1);
        ArrayList<View> squares = new ArrayList<View>();
        squares.add(square1);
        squares.add(square2);
        squares.add(square3);
        AAnimationSet fadeAnimations = fade.apply(squares);
        fadeAnimations.run();

        TranslateAAnimationFactory translate = new TranslateAAnimationFactory(30, 50);
        AAnimationSet translateAnimations = translate.apply(squares);
        translateAnimations.run();

        RotateAAnimationFactory rotate = new RotateAAnimationFactory(85);
        AAnimationSet rotateAnimations = rotate.apply(squares);
        rotateAnimations.run();

        ScaleAAnimationFactory scale = new ScaleAAnimationFactory(0.5f);
        AAnimationSet scaleAnimations = scale.apply(squares);
        scaleAnimations.run();
    }

}
