package com.example.languagelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_add = findViewById(R.id.btn_add_reminder);
        button_add.setOnClickListener(v -> addNewModule());
        Button button_learn = findViewById(R.id.learn1);
        button_learn.setOnClickListener(v -> startLearning());
    }

    public void addNewModule() {
        Intent intent = new Intent(this, AddModule.class);
        startActivity(intent);
    }

    public void startLearning(){
        Intent intent = new Intent(this, LearnModule.class);
        startActivity(intent);
    }
}