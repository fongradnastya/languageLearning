package com.example.languagelearning;

public interface FlashCardState {
    void flip(FlashCard flashcard);
    String getValue();
    void setValue(String value);
}
