package com.example.languagelearning;

import java.util.ArrayList;
import java.util.List;

public class FlashCardIterator implements Iterator {
    private List<FlashCard> flashcards;
    private int position;

    public FlashCardIterator(List<FlashCard> flashcards) {
        this.flashcards = new ArrayList<>();
        for (FlashCard flashcard : flashcards) {
            if (flashcard.isReadyToShow()) {
                this.flashcards.add(flashcard);
            }
        }
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < flashcards.size();
    }

    @Override
    public Object next() {
        FlashCard flashcard = flashcards.get(position);
        position++;
        return flashcard;
    }

    public void moveToBack(FlashCard flashcard) {
        int index = -1;
        for (int i = 0; i < flashcards.size(); i++) {
            if (flashcards.get(i) == flashcard) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            flashcards.remove(index);
            flashcards.add(flashcard);
        }
    }
}
