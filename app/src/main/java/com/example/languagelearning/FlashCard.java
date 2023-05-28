package com.example.languagelearning;

import java.sql.Timestamp;

public class FlashCard {
    private int responseNumber;

    private String lastAnswer;

    private Timestamp lastTime;

    private boolean isHidden;

    private Word word;

    public boolean getIsHidden(){
        return isHidden;
    }

    public FlashCard(Word word) {
        this.word = word;
        responseNumber = 0;
        lastAnswer = null;
        lastTime = null;
        isHidden = true;
    }

    public Word getWord(){
        return word;
    }
    public void flipCard(){
        isHidden = !isHidden;
    }
}
