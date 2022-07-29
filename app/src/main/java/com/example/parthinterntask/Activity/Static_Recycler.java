package com.example.parthinterntask.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parthinterntask.R;

public class Static_Recycler extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_recycler);

        RelativeLayout relativeLayout = findViewById(R.id.relLayout);
        View view = LayoutInflater.from(this).inflate(R.layout.card4, null);
        view.setId(1);

        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        relativeLayout.addView(view, relativeParams);


        View view1 = LayoutInflater.from(this).inflate(R.layout.card4, null);
        RelativeLayout.LayoutParams relativeParams1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeParams1.addRule(RelativeLayout.BELOW, view.getId());
        relativeLayout.addView(view1, relativeParams1);
    }
}