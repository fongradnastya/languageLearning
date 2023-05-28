package com.example.languagelearning;

import java.util.ArrayList;
import java.util.List;

public class TrainingSession {
    private Module learningModule;
    private List<FlashCard> cardsToLearn;

    private FlashCardIterator iterator;
    private int currentCardId;

    public TrainingSession(Module learningModule) {
        this.learningModule = learningModule;
        List<Word> words = learningModule.getFlashCards();
        cardsToLearn = new ArrayList<>();
        for(Word word: words){
            FlashCard card = new FlashCard(word);
            cardsToLearn.add(card);
        }
        currentCardId = 0;
        iterator = new FlashCardIterator(cardsToLearn);
    }

    public void saveAnswer(String response){
        FlashCard card = getCurrentCard();
        card.addResponse(response);
        if(response.equals("forgot")){
            iterator.moveToBack(card);
        }
    }

    public FlashCard getNextCard(){
        if(currentCardId + 1 < cardsToLearn.size()){
            currentCardId += 1;
            FlashCard newCard = cardsToLearn.get(currentCardId);
            return newCard;
        }
        return null;
    }
    public FlashCard getCurrentCard(){
        FlashCard card = cardsToLearn.get(currentCardId);
        return card;
    }

    public int getCardsNumber(){
        return cardsToLearn.size();
    }

    public int getCurrentCardId(){
        return currentCardId;
    }

}
