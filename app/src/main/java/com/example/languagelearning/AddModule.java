package com.example.languagelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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