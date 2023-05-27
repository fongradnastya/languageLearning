package com.example.languagelearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        Button addCard = findViewById(R.id.add_card_btn);
        addCard.setOnClickListener(v -> addNewCard());
        button.setOnClickListener(v -> openReminderAddActivity());
        submitButton.setOnClickListener(v -> serializeForm());
        Context context = getApplicationContext();
        AssetManager assets = context.getAssets();
        // InputStream inputStream = assets.open("data.csv");
    }
    public void openReminderAddActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addNewCard() {
        TextView title = findViewById(R.id.cards_title);
        ConstraintLayout layout = findViewById(R.id.layout);
        EditText editWord = new EditText(AddModule.this);
        editWord.setLayoutParams(new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        editWord.setHint("The word");
        editWord.setId(View.generateViewId());
        layout.addView(editWord);
        EditText editTranslation = new EditText(AddModule.this);
        editTranslation.setLayoutParams(new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        editTranslation.setId(View.generateViewId());
        editTranslation.setHint("The translation");
        layout.addView(editTranslation);
        ConstraintSet constraintSet1 = new ConstraintSet();
        constraintSet1.clone(layout);
        constraintSet1.connect(editWord.getId(), ConstraintSet.TOP, title.getId(), ConstraintSet.TOP);
        constraintSet1.connect(editWord.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START);
        constraintSet1.connect(editWord.getId(), ConstraintSet.END, layout.getId(), ConstraintSet.END);
        constraintSet1.connect(editTranslation.getId(), ConstraintSet.TOP, editWord.getId(), ConstraintSet.TOP);
        constraintSet1.connect(editTranslation.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START);
        constraintSet1.connect(editTranslation.getId(), ConstraintSet.END, layout.getId(), ConstraintSet.END);
        constraintSet1.applyTo(layout);

    }


    public void serializeForm(){
        Word newWord;
        String word = wordEditText.getText().toString();
        String translation = translationEditText.getText().toString();
    }
}