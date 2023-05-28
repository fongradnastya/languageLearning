package com.example.languagelearning;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AddModule extends AppCompatActivity {

    private LinearLayout mLayout;
    private Button mButton;
    private Button mSubmitButton;

    private ModuleFactory moduleFactory;

    private int wordsNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        wordsNumber = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_module);
        mLayout = (LinearLayout) findViewById(R.id.layout);
        mButton = (Button) findViewById(R.id.new_button);
        mSubmitButton = (Button) findViewById(R.id.submit_button);
        Button exitBtn = findViewById(R.id.exit_btn);
        exitBtn.setOnClickListener(v -> exit());
        moduleFactory = new ModuleFactory();
        mButton.setOnClickListener(v -> addNewWord());
        mSubmitButton.setOnClickListener(v -> serializeForm());
    }
    private void addNewWord(){
        int index = mLayout.indexOfChild(mSubmitButton);
        mLayout.addView(createNewText("Card"), index);
        mLayout.addView(createNewEditText("The word"), index + 1);
        mLayout.addView(createNewEditText("The translation"), index + 2);
        mLayout.addView(createNewButton(), index + 3);
        wordsNumber += 1;
    }
    private EditText createNewEditText(String hint_text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final EditText editText = new EditText(this);
        editText.setHint(hint_text);
        editText.setLayoutParams(lparams);
        return editText;
    }

    private TextView createNewText(String text){
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView newText = new TextView(this);
        newText.setText(text);
        newText.setLayoutParams(lparams);
        return newText;
    }

    private Button createNewButton() {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final Button button = new Button(this);
        button.setLayoutParams(lparams);
        button.setText("Delete");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = mLayout.indexOfChild(v);
                mLayout.removeViewAt(index - 1);
                mLayout.removeViewAt(index - 2);
                mLayout.removeViewAt(index - 3);
                mLayout.removeView(v);
            }
        });
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serializeForm();
            }
        });
        return button;
    }
    private void serializeForm() {
        DatabaseManager manager = new DatabaseManager(this);

        List<String> strings = getAllValues();
        String moduleName = strings.get(0);
        String moduleDescription = strings.get(1);
        List<Word> words = new ArrayList<>();
        for (int i = 2; i <= wordsNumber * 2; i += 2) {
            Word word = new Word(strings.get(i), strings.get(i + 1), moduleName);
            words.add(word);
        }
        System.out.println(words);
        Module newModule = moduleFactory.createModule(moduleName, moduleDescription, words);
        if (newModule != null){
            manager.insertModule(newModule);
            manager.insertCards(words);
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private List<String> getAllValues(){
        List<String> values = new ArrayList<>();
        LinearLayout rootView = (LinearLayout) findViewById(R.id.layout);
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View view = rootView.getChildAt(i);
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                String text = editText.getText().toString();
                values.add(text);
            }
        }
        return values;
    }
    private void exit(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}