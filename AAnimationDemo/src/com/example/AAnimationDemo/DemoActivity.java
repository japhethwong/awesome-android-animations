package com.example.AAnimationDemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class DemoActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /**
     * Response handler to react to taps on our button which toggles animations.
     * @param v is the view which registered the tap
     */
    public void onClick(View v) {
        Log.d("HEY", "got clicked");
    }

}
