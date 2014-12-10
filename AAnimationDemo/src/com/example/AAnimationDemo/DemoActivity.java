package com.example.AAnimationDemo;

import android.animation.Animator;
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
    private final static int WAIT = 100;
    int count = 0;
    View square1, square2, square3;
    Button button1, button2, button3;
    AAnimationSet animationSet;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScreen();
    }

    private void setScreen() {
        setContentView(R.layout.main);
        square1 = findViewById(R.id.square1);
        square2 = findViewById(R.id.square2);
        square3 = findViewById(R.id.square3);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
    }

    /**
     * changeToStartAnimationButton() is a helper function which toggles the state of our button to start animation.
     */
    private void changeToStartAnimationButton(Button b, String example) {
        b.setText("Start " + example + " Animation Demo");
    }

    /**
     * changeToCancelAnimationButton() is a helper function which toggles the state of our button to reset animation.
     */
    private void changeToCancelAnimationButton(Button b, String example) {
        b.setText("Cancel " + example + " Animation Demo");
    }

    public void onClick(final View v) {
        int viewId = v.getId();
        String example = "";

        if (viewId == R.id.resetButton) {
            handleResetButton();
            return;
        }
        if (animationSet != null && animationSet.isRunning()) {
            animationSet.cancel();
        } else {
            switch (viewId) {
                case R.id.button1:
                    animationSet = run1WithFactory();
                    example = "EXAMPLE 1";
                    break;
                case R.id.button2:
                    animationSet = run2WithFactory();
                    example = "EXAMPLE 2";
                    break;
                case R.id.button3:
                    animationSet = run3WithFactory();
                    example = "EXAMPLE 3";
                    break;
                case R.id.easyButton:
                    animationSet = runEasyDemo();
                    example = "EASY";
                    break;
                case R.id.mediumButton:
                    animationSet = runMediumDemo();
                    example = "MEDIUM";
                    break;
                default:
                    Log.d("onClick", "Reached default case in onClick, view ID was: " + viewId);    
            }

            final String exampleString = example;

            if (animationSet != null) {
                animationSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        changeToCancelAnimationButton((Button)v, exampleString);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        changeToStartAnimationButton((Button)v, exampleString);
                        animationSet = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        changeToStartAnimationButton((Button)v, exampleString);
                        animationSet = null;
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animationSet.run();
            }
        }
    }

    public void handleResetButton() {
        if (animationSet != null && animationSet.isRunning()) {
            animationSet.cancel();
        }
        setScreen();
    }

    private AAnimationSet run1WithFactory() {
        ArrayList<View> squares = new ArrayList<View>();
        squares.add(square1);
        squares.add(square2);
        squares.add(square3);

        FadeAAnimationFactory fade = new FadeAAnimationFactory(0,1,TIME,WAIT);
        TranslateAAnimationFactory translate = new TranslateAAnimationFactory(30, 5, TIME, WAIT);
        RotateAAnimationFactory rotate = new RotateAAnimationFactory(-700, TIME, WAIT);
        ScaleAAnimationFactory scale = new ScaleAAnimationFactory(0.8f, TIME, WAIT);

        List<AAnimationFactory> animations = new ArrayList<AAnimationFactory>();
        animations.add(fade);
        animations.add(translate);
        animations.add(rotate);
        animations.add(scale);

        LinearAAnimationSetFactory linearAnimation = new LinearAAnimationSetFactory(animations);
        AAnimationSet set = linearAnimation.apply(squares);
        return set;
    }

    private AAnimationSet run2WithFactory() {
        // TODO replace with ex. 2 from design doc
        ArrayList<View> squares = new ArrayList<View>();
        squares.add(square1);
        squares.add(square2);
        squares.add(square3);

        FadeAAnimationFactory fadeIn = new FadeAAnimationFactory(0,1,TIME*2,WAIT);
        FadeAAnimationFactory fadeOut = new FadeAAnimationFactory(1, 0, TIME, 0);
        TranslateAAnimationFactory translate = new TranslateAAnimationFactory(30, 5, TIME, 0);
        RotateAAnimationFactory rotate = new RotateAAnimationFactory(-700, TIME, 0);
        ScaleAAnimationFactory scale = new ScaleAAnimationFactory(0.9f, TIME, 0);

        List<AAnimationFactory> translateRotate = new ArrayList<AAnimationFactory>();
        translateRotate.add(translate);
        translateRotate.add(rotate);

        LinearAAnimationSetFactory linAnim12 = new LinearAAnimationSetFactory(translateRotate);

        List<AAnimationFactory> fadeScale = new ArrayList<AAnimationFactory>();
        fadeScale.add(fadeIn);
        fadeScale.add(scale);
        fadeScale.add(fadeOut);

        LinearAAnimationSetFactory linAnim3 = new LinearAAnimationSetFactory(fadeScale);

        List<AAnimationFactory> anims = new ArrayList<AAnimationFactory>();
        anims.add(linAnim12);
        anims.add(linAnim3);

        ParallelAAnimationSetFactory par = new ParallelAAnimationSetFactory(anims);
        AAnimationSet animSet = par.apply(squares);
        return animSet;
    }

    private AAnimationSet run3WithFactory() {
        ArrayList<View> squares = new ArrayList<View>();
        squares.add(square1);
        squares.add(square2);
        FadeAAnimationFactory fadeIn = new FadeAAnimationFactory(0,1,TIME*2,WAIT);
        TranslateAAnimationFactory translate = new TranslateAAnimationFactory(30, 5, TIME, 0);
        List<AAnimationFactory> fadeTrans = new ArrayList<AAnimationFactory>();
        fadeTrans.add(fadeIn);
        fadeTrans.add(translate);
        LinearAAnimationSetFactory linAnim1 = new LinearAAnimationSetFactory(fadeTrans);

        RotateAAnimationFactory rotate = new RotateAAnimationFactory(-700, TIME, 0);
        ScaleAAnimationFactory scale = new ScaleAAnimationFactory(0.9f, TIME, 0);
        List<AAnimationFactory> rotScale = new ArrayList<AAnimationFactory>();
        rotScale.add(rotate);
        rotScale.add(scale);
        LinearAAnimationSetFactory linAnim2 = new LinearAAnimationSetFactory(fadeTrans);

        List<AAnimationFactory> anims = new ArrayList<AAnimationFactory>();
        anims.add(linAnim1);
        anims.add(linAnim2);
        ParallelAAnimationSetFactory par = new ParallelAAnimationSetFactory(anims);
        AAnimationSet animSet1 = par.apply(square1);

        return null;
    }

    /**
     * runEasyDemo() runs the easy demo in our design document.
     * @return an AAnimationSet which corresponds to this animation
     */
    private AAnimationSet runEasyDemo() {
        // Create the component fade animation factories.
        FadeAAnimationFactory fadeIn = new FadeAAnimationFactory(0f, 1f, TIME*2, WAIT);
        FadeAAnimationFactory fadeOut = new FadeAAnimationFactory(1f, 0f, TIME*2, WAIT);

        // Create the list to contain the sequence of animations.
        List<AAnimationFactory> sequence = new ArrayList<AAnimationFactory>();
        sequence.add(fadeIn);
        sequence.add(fadeOut);

        // Compose the animations and apply the sequence to the squares.
        return new LinearAAnimationSetFactory(sequence).apply(square1);
    }

    private AAnimationSet runMediumDemo() {
        // Create list of squares to operate on.
        ArrayList<View> squares = new ArrayList<View>();
        squares.add(square1);
        squares.add(square2);
        squares.add(square3);

        // Initialize the component animation factories.
        FadeAAnimationFactory fadeIn = new FadeAAnimationFactory(0,1,TIME,WAIT);
        TranslateAAnimationFactory translate = new TranslateAAnimationFactory(30, 5, TIME, WAIT);
        RotateAAnimationFactory rotate = new RotateAAnimationFactory(-700, TIME, WAIT);
        FadeAAnimationFactory fadeOut = new FadeAAnimationFactory(1, 0, TIME, WAIT);

        // Add the component animation factories into a list.
        List<AAnimationFactory> animations = new ArrayList<AAnimationFactory>();
        animations.add(fadeIn);
        animations.add(translate);
        animations.add(rotate);
        animations.add(fadeOut);

        // Compose individual animation factories into a sequence, and apply it to the list of targets.
        LinearAAnimationSetFactory linearAnimation = new LinearAAnimationSetFactory(animations);
        return linearAnimation.apply(squares);
    }
}
