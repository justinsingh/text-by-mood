package com.textbymood.javaspringbootbackend.models;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Result.java --- A class for representing the relationship between a body of text and a lexicon.
 * 
 * @author Justin Singh
 */
public class TextAnalysisResult {
    final String text;
    final List<String> topDescriptors;
    final Map<String, Integer> descriptorScoreMap;
    final Map<String, Set<String>> descriptiveWordsMap;

    public TextAnalysisResult(String text, List<String> topDescriptors, Map<String, Integer> descriptorScoreMap, Map<String, Set<String>> descriptiveWordsMap) {
        this.text = text;
        this.topDescriptors = topDescriptors;
        this.descriptorScoreMap = descriptorScoreMap;
        this.descriptiveWordsMap = descriptiveWordsMap;
    }
}
