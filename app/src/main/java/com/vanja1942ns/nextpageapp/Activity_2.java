package com.vanja1942ns.nextpageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_2 extends AppCompatActivity {

    private Button mButton2;
    private Button mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });

    }
    public void openActivity1(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void openActivity3(){
        Intent intent = new Intent(this,Activity_3.class);
        startActivity(intent);
    }
}


