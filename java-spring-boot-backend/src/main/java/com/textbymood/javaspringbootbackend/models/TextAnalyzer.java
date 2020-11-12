package com.textbymood.javaspringbootbackend.models;

/**
 * TextAnalyzer.java --- A class for processing body of texts to determine their relationship to a lexicon.
 * 
 * @author Justin Singh
 */
public class TextAnalyzer {
    private final Lexicon lexicon;

    public TextAnalyzer(Lexicon lexicon) {
        this.lexicon = lexicon;
    }
}
