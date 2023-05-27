package com.example.languagelearning;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class AddModule extends AppCompatActivity {

    private LinearLayout mLayout;
    private Button mButton;

    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_module);
        mLayout = (LinearLayout) findViewById(R.id.layout);
        mButton = (Button) findViewById(R.id.new_button);
        mSubmitButton = (Button) findViewById(R.id.submit_button);
        Button exitBtn = findViewById(R.id.exit_btn);
        exitBtn.setOnClickListener(v -> exit());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add two EditText views
                int index = mLayout.indexOfChild(mSubmitButton);
                mLayout.addView(createNewText("Card"), index);
                mLayout.addView(createNewEditText("The word"), index + 1);
                mLayout.addView(createNewEditText("The translation"), index + 2);
                mLayout.addView(createNewButton(), index + 3);
            }
        });
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
        EditText moduleNameEditText = (EditText) findViewById(R.id.name_edit);
        EditText moduleDescriptionEditText = (EditText) findViewById(R.id.description_edit);
        String moduleName = moduleNameEditText.getText().toString();
        String moduleDescription = moduleDescriptionEditText.getText().toString();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mLayout.getChildCount(); i++) {
            View view = mLayout.getChildAt(i);
            if (view instanceof EditText) {
                sb.append(((EditText) view).getText().toString());
                sb.append("\n");
            }
        }
        System.out.println("Form Data");
        System.out.println(sb);
    }
    private void exit(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}