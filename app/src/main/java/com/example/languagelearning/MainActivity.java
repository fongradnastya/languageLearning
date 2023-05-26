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
        Button button = findViewById(R.id.btn_add_reminder);
        button.setOnClickListener(v -> openReminderAddActivity());
    }

    public void openReminderAddActivity() {
        Intent intent = new Intent(this, AddModule.class);
        startActivity(intent);
    }
}