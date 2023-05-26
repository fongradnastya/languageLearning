package com.example.languagelearning;

public class Word {
    private String word;
    private String translation;
    private String group;

    // Constructor
    public Word(String word, String translation, String group) {
        this.word = word;
        this.translation = translation;
        this.group = group;
    }

    // Getters
    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public String getGroup() {
        return group;
    }

    // Override toString method to format the data as a csv row
    @Override
    public String toString() {
        return word + "," + translation + "," + group;
    }
}