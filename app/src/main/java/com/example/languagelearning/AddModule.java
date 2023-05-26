package com.example.languagelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.InputStream;

public class AddModule extends AppCompatActivity {

    private EditText wordEditText;
    private EditText translationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_module);
        Button button = findViewById(R.id.exit_btn);
        Button submitButton = findViewById(R.id.btn_submit);
        button.setOnClickListener(v -> openReminderAddActivity());
        submitButton.setOnClickListener(v -> serializeForm());
        Context context = getApplicationContext();
        AssetManager assets = context.getAssets();
        InputStream inputStream = assets.open("data.csv");
    }
    public void openReminderAddActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void serializeForm(){
        Word newWord;
        String word = wordEditText.getText().toString();
        String translation = translationEditText.getText().toString();

    }
}