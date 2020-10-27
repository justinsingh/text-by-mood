package com.textbymood.javaspringbootbackend.models;

/**
 * TextAnalyzer.java --- A class for determining the relationship between a body of text and a lexicon.
 * 
 * @author Justin Singh
 */
public class TextAnalyzer {
    private final Lexicon lexicon;

    public TextAnalyzer(Lexicon lexicon) {
        this.lexicon = lexicon;
    }
}
