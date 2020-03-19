package com.example.amir.simpletodo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by amir on 3/9/20.
 */

public class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int MIN_SWIPE_DISTANCE = 100;
    private static final int MAX_SWIPE_DISTANCE = 1000;

    private MainActivity activity = null;
    private View view;

    public DetectSwipeGestureListener(MainActivity activity) {
        this.activity = activity;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }


    public Activity getActivity() {
        return this.activity;
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return this.view;
    }



    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        Intent intent = null;

        float deltaX = e1.getX() - e2.getX();
        float deltaY = e1.getY() - e2.getY();

        float deltaXAbs = Math.abs(deltaX);
        float deltaYAbs = Math.abs(deltaY);

        if (deltaYAbs >=MIN_SWIPE_DISTANCE && deltaYAbs <=MAX_SWIPE_DISTANCE && deltaY > 0) {

            view.setVisibility(View.VISIBLE);
            showSoftKeyboard();


        } else {
            if (view.getVisibility() == View.VISIBLE) {
                view.setVisibility(View.INVISIBLE);
                hideSoftKeyboard();
            } else {

                intent = new Intent(this.getActivity(), StatusActivity.class);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this.activity, this.view, "title");
                getActivity().startActivity(intent, activityOptions.toBundle());

            }
        }

        return true;
    }


    public void showSoftKeyboard() {

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }

    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS,1);
    }




}
