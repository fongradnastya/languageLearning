package com.example.languagelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AddModule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_module);
        Button button = findViewById(R.id.exit_btn);
        button.setOnClickListener(v -> openReminderAddActivity());
    }
    public void openReminderAddActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}