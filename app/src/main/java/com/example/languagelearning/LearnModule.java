package com.example.languagelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LearnModule extends AppCompatActivity {

    private boolean isHidden;

    private TrainingSession trainingSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_module);
        this.isHidden = true;
        setLearningModule();
        Button button_exit = findViewById(R.id.exit);
        button_exit.setOnClickListener(v -> exit());
        Button button_translate = findViewById(R.id.translate);
        button_translate.setOnClickListener(v -> showTranslation());
    }

    public void setLearningModule(){
        Intent intent = getIntent();
        String moduleName = intent.getStringExtra("moduleName");
        DatabaseManager manager = new DatabaseManager(this);
        Module module = manager.getModuleByName(moduleName);
        if(module != null){
            trainingSession = new TrainingSession(module);
        }
        setModuleName(moduleName);
        setCardNumber();
    }

    public void setModuleName(String moduleName){
        if(moduleName != null){
            TextView header = findViewById(R.id.module_name);
            header.setText(moduleName);
        }
    }

    public void setCardNumber(){
        TextView cardNumber = findViewById(R.id.card_number);
        int currentNumber = trainingSession.getCurrentCardId() + 1;
        int commonNumber = trainingSession.getCardsNumber();
        String value = currentNumber + "/" + commonNumber;
        cardNumber.setText(value);
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