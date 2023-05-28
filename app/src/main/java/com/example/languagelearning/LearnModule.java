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
        processAnswers();
    }

    public void processAnswers(){
        Button difficultAnswer = findViewById(R.id.difficult);
        Button forgotAnswer = findViewById(R.id.forgot);
        Button easyAnswer = findViewById(R.id.easy);
        difficultAnswer.setOnClickListener(v -> showNewCard());
        forgotAnswer.setOnClickListener(v -> showNewCard());
        easyAnswer.setOnClickListener(v -> showNewCard());
    }

    public void showNewCard(){
        if(!trainingSession.getCurrentCard().getIsHidden()){
            showTranslation();
        }
        FlashCard card = trainingSession.getNextCard();
        if(card == null){
            exit();
        }
        else {
            setCardNumber();
            showWord();
        }
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
        showWord();
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

    public void showWord(){
        TextView text = findViewById(R.id.word);
        FlashCard card = trainingSession.getCurrentCard();
        Word word = card.getWord();
        text.setText(word.getWord());
    }

    public void showTranslation(){
        TextView text = findViewById(R.id.translation);
        Button button_translate = findViewById(R.id.translate);
        FlashCard card = trainingSession.getCurrentCard();
        Word word = card.getWord();
        if (card.getIsHidden()){
            text.setText(word.getTranslation());
            button_translate.setText("Hide");
        }
        else{
            text.setText("");
            button_translate.setText("Translate");
        }
        card.flipCard();
    }
}