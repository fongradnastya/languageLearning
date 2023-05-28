package com.example.languagelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager manager;
    private int modulesCnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modulesCnt = 0;
        setContentView(R.layout.activity_main);
        Button button_add = findViewById(R.id.add_module);
        button_add.setOnClickListener(v -> addNewModule());
        manager = new DatabaseManager(this);
        List<Module> modules = manager.getAllModules();
        for(Module module : modules){
            createNewModule(module);
        }
    }

    public void addNewModule() {
        Intent intent = new Intent(this, AddModule.class);
        startActivity(intent);
    }

    public void createNewModule(Module module){
        modulesCnt += 1;
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.layout);
        LinearLayout newModuleLayout = new LinearLayout(this);
        newModuleLayout.setTag(module.getModuleName());
        // newModuleLayout.setBackgroundColor(0xFFBB86FC);
        newModuleLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(50, 50, 50, 50);
        newModuleLayout.setLayoutParams(layoutParams);
        LinearLayout textLayout = new LinearLayout(this);
        textLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f);
        textLayout.setLayoutParams(textLayoutParams);

        TextView nameTextView = createTextView(module.getModuleName(), 30);
        TextView infoTextView = createTextView(module.getModuleDescription(), 20);
        textLayout.addView(nameTextView);
        textLayout.addView(infoTextView);
        Button learnButton = createButton("Learn");
        Button deleteButton = createButton("X");
        newModuleLayout.addView(textLayout);
        newModuleLayout.addView(learnButton);
        newModuleLayout.addView(deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseManager databaseManager = new DatabaseManager(MainActivity.this);
                databaseManager.deleteModule(module.getModuleName());
                ((ViewGroup) newModuleLayout.getParent()).removeView(newModuleLayout);
            }
        });
        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LearnModule.class);
                intent.putExtra("moduleName", module.getModuleName());
                startActivity(intent);
            }
        });
        parentLayout.addView(newModuleLayout, 1);
    }
    private Button createButton(String text){
        Button button = new Button(this);
        button.setText(text);
        LinearLayout.LayoutParams learnButtonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        learnButtonParams.topMargin = 10;
        button.setLayoutParams(learnButtonParams);
        return button;
    }
    private TextView createTextView(String text, int textSize){
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(textSize);
        LinearLayout.LayoutParams nameTextViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        nameTextViewParams.setMarginStart(10);
        textView.setLayoutParams(nameTextViewParams);
        return textView;
    }
}