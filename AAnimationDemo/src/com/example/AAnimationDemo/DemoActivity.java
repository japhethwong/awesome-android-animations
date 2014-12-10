package com.example.AAnimationDemo;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.cs164.AAnimation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoActivity extends Activity {
    private final static int TIME = 500;
    int count = 0;
    View square1, square2, square3;
    Button button1, button2, button3;
    AAnimationSet set1, set2, set3;
    Map<Integer, AAnimationState> viewsMap;
    boolean coordsSaved;
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

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        viewsMap = new HashMap<Integer, AAnimationState>();
        coordsSaved = false;
    }

    /**
     * changeToStartAnimationButton() is a helper function which toggles the state of our button to start animation.
     */
    private void changeToStartAnimationButton(Button b, int example) {
        b.setText("Start animation ex. "+example);
    }

    /**
     * changeToCancelAnimationButton() is a helper function which toggles the state of our button to reset animation.
     */
    private void changeToCancelAnimationButton(Button b, int example) {
        b.setText("Cancel animation ex. "+example);
    }

    /**
     * Response handler to react to taps on our button which toggles animations.
     * @param v is the view which registered the tap
     */
    public void onClickEx1(View v) {
        Log.d("HEY", "got clicked " + count);
        count++;
        saveInitialValues();
        if(set1 != null && set1.isRunning()) {
            set1.cancel();
        } else {
            //      runBasic();
            set1 = runWithFactory();
            set1.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    changeToCancelAnimationButton(button1, 1);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    changeToStartAnimationButton(button1, 1);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    changeToStartAnimationButton(button1, 1);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            set1.run();
        }
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

    /**
     * Response handler to react to taps on our button which toggles animations.
     * @param v is the view which registered the tap
     */
    public void onClickEx2(View v) {
        // TODO: Replace with the actual demo code.
        saveInitialValues();
        ArrayList<View> squares = new ArrayList<View>();
        squares.add(square1);
        squares.add(square2);
        squares.add(square3);

        FadeAAnimationFactory fadeIn = new FadeAAnimationFactory(0,1,TIME*2,100);
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
        animSet.run();
    }

    /**
     * Response handler to react to taps on our button which toggles animations.
     * @param v is the view which registered the tap
     */
    public void onClickEx3(View v) {
        // TODO
        Log.d("Started", "ex. 3");
        saveInitialValues();
    }

    public void onClickReset(View v) {
        Log.d("Reset", "Tapped RESET button.");
        for (Integer key : viewsMap.keySet()) {
            Log.d("RESET", "Setting properties for key: " + key);
            View currentView = findViewById(key);
            AAnimationState state = viewsMap.get(key);
            currentView.setX(state.x);
            currentView.setY(state.y);
            currentView.setRotation(state.rotation);
            currentView.setAlpha(state.alpha);
            currentView.setScaleX(state.scale);
            currentView.setScaleY(state.scale);
            currentView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * This is a hack for reset.  There has to be a better way to do this.
     **/
    private void saveInitialValues() {
        if (!coordsSaved) {
            viewsMap.put(R.id.square1, new AAnimationState(square1));
            viewsMap.put(R.id.square2, new AAnimationState(square2));
            viewsMap.put(R.id.square3, new AAnimationState(square3));
            coordsSaved = true;
        }
    }

    private AAnimationSet runWithFactory() {
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
        return set;
//        set.run();
    }

}
