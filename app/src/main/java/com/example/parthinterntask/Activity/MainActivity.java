package com.example.parthinterntask.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parthinterntask.R;

public class MainActivity extends AppCompatActivity {

    private ImageView ivLogo;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivLogo = findViewById(R.id.ivLogo);
        Log.d("Check Handler","Handler is Starting !!" + handler);
        handler = new Handler(Looper.getMainLooper());

        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity1.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(runnable , 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Check Destroy","Handler is Removing !!");
//        handler.removeCallbacks(runnable);
    }
}