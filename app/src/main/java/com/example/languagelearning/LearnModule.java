package com.example.languagelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LearnModule extends AppCompatActivity {

    private boolean isHidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.isHidden = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_module);
        Button button_exit = findViewById(R.id.exit);
        button_exit.setOnClickListener(v -> exit());
        Button button_translate = findViewById(R.id.translate);
        button_translate.setOnClickListener(v -> showTranslation());
        CSVHandler.readData("")
    }

    public void exit(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showTranslation(){
        TextView text = findViewById(R.id.translation);
        Button button_translate = findViewById(R.id.translate);
        if (this.isHidden){
            text.setText("Translation");
            button_translate.setText("Hide");
        }
        else{
            text.setText("");
            button_translate.setText("Translate");
        }
        this.isHidden = ! this.isHidden;
    }
}