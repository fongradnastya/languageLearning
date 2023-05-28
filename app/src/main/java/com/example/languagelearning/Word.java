package com.example.languagelearning;

public class Word {
    private String word;
    private String translation;
    private String module;

    public Word(String word, String translation, String module) {
        this.word = word;
        this.translation = translation;
        this.module = module;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String newWord){
        this.word = newWord;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation){
        this.translation = translation;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module){
        this.module = module;
    }

    @Override
    public String toString() {
        return word + "," + translation + "," + module;
    }
}