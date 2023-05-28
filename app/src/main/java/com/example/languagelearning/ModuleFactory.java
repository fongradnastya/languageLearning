package com.example.languagelearning;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModuleFactory {
    private Set<String> moduleNames;

    public ModuleFactory() {
        moduleNames = new HashSet<>();
    }

    public Module createModule(String moduleName, String moduleDescription,
                               List<Word> flashCards) {
        if (moduleNames.contains(moduleName)) {
            return null;
        }

        if (flashCards == null || flashCards.isEmpty()) {
            return null;
        }

        moduleNames.add(moduleName);
        return new Module(moduleName, moduleDescription, flashCards);
    }
}
