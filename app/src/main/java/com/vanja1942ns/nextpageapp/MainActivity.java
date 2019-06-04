package com.vanja1942ns.nextpageapp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private Button mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);


        mButton = findViewById(R.id.button);
        mButton3 = findViewById(R.id.button3);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }
        public void openActivity2(){
            Intent intent = new Intent(this, Activity_2.class);
            startActivity(intent);
        }
    public void openActivity3(){
        Intent intent = new Intent(this, Activity_3.class);
        startActivity(intent);
    }
    }

