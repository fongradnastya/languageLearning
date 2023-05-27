package com.example.languagelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_add = findViewById(R.id.add_module);
        button_add.setOnClickListener(v -> addNewModule());
        DatabaseManager manager = new DatabaseManager(this);
        List<Module> modules = manager.getAllModules();
        for(Module module : modules){
            createNewModule(module);
        }
    }

    public void addNewModule() {
        //List<Module> modules;
        Intent intent = new Intent(this, AddModule.class);
        //intent.putParcelableArrayListExtra("module", modules);
        startActivity(intent);
    }

    public void startLearning(){
        Intent intent = new Intent(this, LearnModule.class);
        startActivity(intent);
    }

    public void createNewModule(Module module){
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.layout);
        LinearLayout newModuleLayout = new LinearLayout(this);
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

        TextView nameTextView = new TextView(this);
        nameTextView.setText(module.getModuleName());
        nameTextView.setTextSize(30);
        LinearLayout.LayoutParams nameTextViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        nameTextViewParams.setMarginStart(8);
        nameTextView.setLayoutParams(nameTextViewParams);

        TextView infoTextView = new TextView(this);
        infoTextView.setText(module.getModuleDescription());
        infoTextView.setTextSize(20);
        LinearLayout.LayoutParams infoTextViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        infoTextViewParams.setMarginStart(8);
        infoTextView.setLayoutParams(infoTextViewParams);

        textLayout.addView(nameTextView);
        textLayout.addView(infoTextView);

        Button learnButton = new Button(this);
        learnButton.setText("Learn");
        LinearLayout.LayoutParams learnButtonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        learnButtonParams.topMargin = 10;
        learnButton.setLayoutParams(learnButtonParams);
        newModuleLayout.addView(textLayout);
        newModuleLayout.addView(learnButton);
        learnButton.setOnClickListener(v -> startLearning());
        parentLayout.addView(newModuleLayout, 1); // Change the index to add below a specific module

    }
}