package com.example.languagelearning;

import java.util.List;

public class Module {
    private String moduleName;
    private String moduleDescription;
    private List<FlashCard> flashCards;

    public Module(String moduleName, String moduleDescription) {
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.flashCards = null;
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

    public List<FlashCard> getFlashCards() {
        return flashCards;
    }

    public void setFlashCards(List<FlashCard> flashCards) {
        this.flashCards = flashCards;
    }
}

