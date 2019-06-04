package com.vanja1942ns.nextpageapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Activity_3 extends AppCompatActivity {
    ArrayList<String> shoppingList = null;
    ArrayAdapter<String> adapter = null;
    ListView lv = null;

    private Button mButton;
    private Button mButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shoppingList = getArrayVal(getApplicationContext());
        Collections.sort(shoppingList);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, shoppingList);
        lv =  findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItem.trim().equals(shoppingList.get(position).trim())) {
                    removeElement(selectedItem, position);
                } else {
                    Toast.makeText(getApplicationContext(), "Error Removing Element", Toast.LENGTH_LONG).show();
                }
            }
        });

        mButton = findViewById(R.id.button);
        mButton4 = findViewById(R.id.button4);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });
    }

    public void openActivity2(){
        Intent intent = new Intent(this,Activity_2.class);
        startActivity(intent);
    }
    public void openActivity4(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_add)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add New Task");
            final EditText input = new EditText(this);
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    shoppingList.add(preferredCase(input.getText().toString()));
                    Collections.sort(shoppingList);
                    storeArrayVal(shoppingList,getApplicationContext());
                    lv.setAdapter(adapter);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String preferredCase(String original){

        if (original.isEmpty())
            return original;
        return original.substring(0,1).toUpperCase() + original.substring(1).toLowerCase();
    }

    public static void storeArrayVal(ArrayList inArrayList, Context context)
    {
        //Saving data
        Set WhatToWrite = new HashSet(inArrayList);
        SharedPreferences WordSearchPutPrefs = context.getSharedPreferences("dbArrayValues", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = WordSearchPutPrefs.edit();
        prefEditor.putStringSet("myArray", WhatToWrite);
        prefEditor.commit();
    }
    //Getting data
    public static ArrayList getArrayVal(Context dan)
    {

        SharedPreferences WordSearchGetPrefs = dan.getSharedPreferences("dbArrayValues", Activity.MODE_PRIVATE);
        Set tempSet = new HashSet();
        tempSet = WordSearchGetPrefs.getStringSet("myArray", tempSet);
        return new ArrayList<>(tempSet);
    }

    public void removeElement(String selectedItem, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove " + selectedItem + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shoppingList.remove(position);
                Collections.sort(shoppingList);
                storeArrayVal(shoppingList,getApplicationContext());
                lv.setAdapter(adapter);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}

