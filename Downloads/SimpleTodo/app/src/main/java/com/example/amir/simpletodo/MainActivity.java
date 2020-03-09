package com.example.amir.simpletodo;

import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity  {


    private DetectSwipeGestureListener swipeGestureListener;
    private GestureDetectorCompat gestureDetector;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        swipeGestureListener = new DetectSwipeGestureListener(this);
        gestureDetector = new GestureDetectorCompat(this,swipeGestureListener);


    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.gestureDetector.onTouchEvent(motionEvent);

        return super.onTouchEvent(motionEvent);
    }


}
