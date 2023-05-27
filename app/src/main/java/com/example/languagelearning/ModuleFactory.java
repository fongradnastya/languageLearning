package com.example.languagelearning;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModuleFactory {
    private Set<String> moduleNames;

    public ModuleFactory() {
        moduleNames = new HashSet<>();
    }

    public Module createModule(String moduleName, String moduleDescription) {
        if (moduleNames.contains(moduleName)) {
            throw new IllegalArgumentException("Module name already exists");
        }

        // if (flashCards == null || flashCards.isEmpty()) {
            //throw new IllegalArgumentException("Module must have at least one FlashCard");
        //}

        moduleNames.add(moduleName);
        return new Module(moduleName, moduleDescription);
    }
}
