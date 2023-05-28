package com.example.languagelearning;

import android.os.Build;
import java.time.LocalDateTime;


class FrontSide implements FlashCardState {
    private String value;

    @Override
    public void flip(FlashCard flashcard) {
        // Code to show the front side of the flashcard
        flashcard.setState(new BackSide());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}

class BackSide implements FlashCardState {
    private String value;

    @Override
    public void flip(FlashCard flashcard) {
        // Code to show the back side of the flashcard
        flashcard.setState(new FrontSide());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}


public class FlashCard {
    private int responseNumber;

    private String lastAnswer;

    private LocalDateTime lastTime;

    private FlashCardState state;

    private LocalDateTime nextShowTime;

    private Word word;

    public FlashCard(Word word) {
        state = new FrontSide();
        this.word = word;
        responseNumber = 0;
        lastAnswer = null;
        lastTime = null;
        nextShowTime = LocalDateTime.now();
    }

    public void setState(FlashCardState state) {
        this.state = state;
    }

    public String getStringValue(){
        return state.getValue();
    }

    public boolean isReadyToShow() {
        return LocalDateTime.now().isAfter(nextShowTime);
    }

    public void calculateNextShowTime() {
        int delayInHours = 1;
        if (lastAnswer.equals("hard")) {
            delayInHours += responseNumber;
        }
        nextShowTime = lastTime.minusHours(delayInHours);
    }

    public void flip() {
        state.flip(this);
    }

    public Word getWord(){
        return word;
    }

    public void addResponse(String lastAnswer){
        this.lastAnswer = lastAnswer;
        responseNumber += 1;
        lastTime = LocalDateTime.now();
        calculateNextShowTime();
        if(!lastAnswer.equals("forgot")){
            calculateNextShowTime();
        }
        else{
            nextShowTime = LocalDateTime.now();
        }
    }
}
