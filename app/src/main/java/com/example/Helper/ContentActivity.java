package com.example.Helper;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {
    private int category = 0;
    private int position = 0;
    private TextView text_content;
    private ImageView image_content;
    private Typeface typeface;
    private int [] arrayProcess = {R.string.text_process1, R.string.text_process2, R.string.text_process3, R.string.text_process4};
    private int [] arrayGraphic = {R.string.text_graphic1, R.string.text_graphic2, R.string.text_graphic3};
    private int [] arrayOperative = {R.string.text_operative1, R.string.text_operative2, R.string.text_operative3};
    private int [] arrayMemory = {R.string.text_memory1, R.string.text_memory2, R.string.text_memory3};
    private int [] arrayImageProcess = {R.drawable.i9, R.drawable.i7, R.drawable.i9, R.drawable.i7};
    private int [] arrayImageGraphic = {R.drawable.w1050, R.drawable.w1050ti, R.drawable.w3090ti};
    private int [] arrayImageMemmory = {R.drawable.hdd, R.drawable.m2png, R.drawable.ssd};



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        init();
        reciveIntent();
    }

    private void reciveIntent() {
        Intent i = getIntent();

        if(i != null) {
            category = i.getIntExtra("category", 0);
            position = i.getIntExtra("position", 0);
        }
        switch (category) {
            case 0: {
                setTitle(R.string.menu_process);
                image_content.setImageResource(arrayImageProcess[position]);
                text_content.setText(arrayProcess[position]);
            }
                break;
            case 1: {
                setTitle(R.string.menu_graphic);
                image_content.setImageResource(arrayImageGraphic[position]);
                text_content.setText(arrayGraphic[position]);
            }
                break;
            case 2: {
                setTitle(R.string.menu_operative);
                image_content.setImageResource(R.drawable.operative);
                text_content.setText(arrayOperative[position]);
            }
                break;
            case 3: {
                setTitle(R.string.menu_memory);
                image_content.setImageResource(arrayImageMemmory[position]);
                text_content.setText(arrayMemory[position]);
            }
                break;

        }
    }

    private void init()
    {
        text_content = findViewById(R.id.textContent);
        image_content = findViewById(R.id.imageContent);

        typeface = Typeface.createFromAsset(this.getAssets(),"fonts/RobotoSlab-VariableFont_wght.ttf");
        text_content.setTypeface(typeface);
    }
}
