package com.example.languagelearning;

import java.util.List;

public class Module {
    private String moduleName;
    private String moduleDescription;
    private List<Word> flashCards;

    public Module(String moduleName, String moduleDescription, List<Word> flashCards) {
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.flashCards = flashCards;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

    public List<Word> getFlashCards() {
        return flashCards;
    }

    public void setFlashCards(List<Word> flashCards) {
        this.flashCards = flashCards;
    }
}

