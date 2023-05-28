package com.example.languagelearning;

import java.util.List;

public class TrainingSession {
    private Module learningModule;
    private List<Word> cardsToLearn;
    private int currentCardId;

    public TrainingSession(Module learningModule) {
        this.learningModule = learningModule;
        cardsToLearn = learningModule.getFlashCards();
        currentCardId = 0;
    }

    public Word getNextCard(){
        if(currentCardId + 1 < cardsToLearn.size()){
            currentCardId += 1;
            Word newWord = cardsToLearn.get(currentCardId);
            return newWord;
        }
        return null;
    }

    public int getCardsNumber(){
        return cardsToLearn.size();
    }

    public int getCurrentCardId(){
        return currentCardId;
    }

}
