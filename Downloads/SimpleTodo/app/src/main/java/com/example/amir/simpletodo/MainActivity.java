package com.example.amir.simpletodo;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.amir.simpletodo.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity  {


    private DetectSwipeGestureListener swipeGestureListener;
    private GestureDetectorCompat gestureDetector;
    private TextView statusTitle;
    private EditText taskText;
    private static HomeAdapter adapter;
    private CardView cardView;
    private FloatingActionButton fab;
    private List<Task> tasks = initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RecyclerView recyclerView = findViewById(R.id.main_recyclerView);

        adapter = new HomeAdapter(tasks,getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        statusTitle = findViewById(R.id.textTitle);
        cardView = findViewById(R.id.card_insert);
        swipeGestureListener = new DetectSwipeGestureListener(this);
        swipeGestureListener.setView(cardView);


        gestureDetector = new GestureDetectorCompat(this,swipeGestureListener);

        taskText = findViewById(R.id.task_insert);

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();
                task.setDescription(taskText.getText().toString());
                task.setRepeat(false);
                tasks.add(task);
            }
        });




    }

    public List<Task> initData() {
        List<Task> tasks = new ArrayList<>();

        Task task = new Task();
        task.setDescription("add some people to contact");
        tasks.add(task);
        return tasks;
    }

    public TextView getStatusTitle() {
        return this.statusTitle;
    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.gestureDetector.onTouchEvent(motionEvent);

        return super.onTouchEvent(motionEvent);
    }
    



}
