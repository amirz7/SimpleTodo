package com.example.amir.simpletodo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amir on 3/10/20.
 */

public class StatusActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mStatus = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);


        mRecyclerView = findViewById(R.id.recycler_status);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new StatusAdapter(mStatus,getApplicationContext()));



    }

}
