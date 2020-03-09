package com.example.amir.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by amir on 3/9/20.
 */

public class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int MIN_SWIPE_DISTANCE = 100;
    private static final int MAX_SWIPE_DISTANCE = 1000;

    private Activity activity = null;

    public DetectSwipeGestureListener(Activity activity) {
        this.activity = activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }


    public Activity getActivity() {
        return this.activity;
    }



    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        float deltaX = e1.getX() - e2.getX();
        float deltaY = e1.getY() - e2.getY();

        float deltaXAbs = Math.abs(deltaX);
        float deltaYAbs = Math.abs(deltaY);

        if (deltaYAbs >=MIN_SWIPE_DISTANCE && deltaYAbs <=MAX_SWIPE_DISTANCE && deltaY > 0) {
            Log.d("debug", "Swipe to Up");
        } else {
            Log.d("debug", "Swipe to down");
        }

        return true;

    }




}
