package com.example.amir.simpletodo;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.simpletodo.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {


    private DetectSwipeGestureListener swipeGestureListener;
    private GestureDetectorCompat gestureDetector;
    private TextView statusTitle;
    private EditText taskInput;
    private InputMethodManager imm;
    private HomeAdapter adapter;
    private ConstraintLayout bottom_sheet;
    private List<Todo> todos = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private boolean isKeyboardShowing = false;
    private BottomSheetBehavior bottomSheetBehavior;
    private ImageView expand_icon;
    private Button add_btn;
    private RadioButton cancelButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        coordinatorLayout = findViewById(R.id.rootView);



        expand_icon = findViewById(R.id.expand_icon);

        recyclerView = findViewById(R.id.main_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomeAdapter(todos,getApplicationContext());
        recyclerView.setAdapter(adapter);


        //-------init gesture for swipe up and down
        swipeGestureListener = new DetectSwipeGestureListener(this);
        gestureDetector = new GestureDetectorCompat(this,swipeGestureListener);


        taskInput = findViewById(R.id.task_input);
        statusTitle = findViewById(R.id.textTitle);


        //---init bottom_sheet-------

        bottom_sheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setPeekHeight(250);
        bottomSheetBehavior.setHideable(false);


        //when click on bottom_sheet layout call this method

        bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });


        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        hideSoftKeyboard(bottomSheet);
                        expand_icon.setImageResource(R.drawable.ic_expand_icon);
                        break;
                    }
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        expand_icon.setImageResource(R.drawable.ic_expandless_icon);
                        break;
                    }
                    case BottomSheetBehavior.STATE_DRAGGING: {
                        break;
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });


        add_btn =  findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo todo = new Todo();
                todo.setDescription(taskInput.getText().toString());
                adapter.addItem(todo);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });



        cancelButton = findViewById(R.id.radio_Button);





        coordinatorLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        Rect r = new Rect();
                        coordinatorLayout.getWindowVisibleDisplayFrame(r);
                        int screenHeight = coordinatorLayout.getRootView().getHeight();

                        // r.bottom is the position above soft keypad or device button.
                        // if keypad is shown, the r.bottom is smaller than that before.
                        int keypadHeight = screenHeight - r.bottom;


                        if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                            // keyboard is opened
                            if (!isKeyboardShowing) {
                                isKeyboardShowing = true;
                                onKeyboardVisibilityChanged(true);
                            }
                        }
                        else {
                            // keyboard is closed
                            if (isKeyboardShowing) {
                                isKeyboardShowing = false;
                                onKeyboardVisibilityChanged(false);
                            }
                        }
                    }
                });




    }


    private void onKeyboardVisibilityChanged(boolean b) {

        if (!b) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

    }


    public TextView getStatusTitle() {
        return this.statusTitle;
    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.gestureDetector.onTouchEvent(motionEvent);

        return super.onTouchEvent(motionEvent);
    }


    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }





}
