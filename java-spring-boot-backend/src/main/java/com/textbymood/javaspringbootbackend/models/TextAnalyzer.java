package com.textbymood.javaspringbootbackend.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TextAnalyzer.java --- A class for scanning bodies of text to determine their relationships to a lexicon.
 * 
 * @author Justin Singh
 */
public class TextAnalyzer {
    private final Lexicon lexicon;
    private final Map<String, Set<String>> vocabulary;

    public TextAnalyzer(Lexicon lexicon) {
        this.lexicon = lexicon;
        this.vocabulary = lexicon.getVocabulary();
    }

    public TextAnalysisResult analyzeText(String text) {
        String[] words = splitTextToWords(text);

        Map<String, Set<String>> descriptiveWordsMap = createDescriptiveWordsMap(words);
        Map<String, Integer> descriptorScoreMap = createDescriptorScoreMap(descriptiveWordsMap, words);
        List<String> topDescriptors = createTopDescriptorsList(descriptorScoreMap);
        
        return new TextAnalysisResult(text, topDescriptors, descriptorScoreMap, descriptiveWordsMap);
    }

    private String[] splitTextToWords(String text) {
        return Arrays.stream(text.split("\\s+"))
                     .map(word -> word.replaceAll("[^a-zA-Z ]", ""))
                     .map(word -> word.toLowerCase()).filter(word -> !lexicon.getStopWords().contains(word))
                     .toArray(String[]::new);
    }

    private List<String> createDescriptorsOfWordList(String word) {
        return vocabulary.keySet().stream()
                                  .filter(descriptor -> vocabulary.get(descriptor).contains(word))
                                  .collect(Collectors.toList());
    }

    private void addWordToDescriptiveWordsMap(String word, List<String> descriptorsOfWord, Map<String, Set<String>> descriptiveWordsMap) {
        descriptorsOfWord.stream()
                         .forEach(descriptor -> {
                             descriptiveWordsMap.putIfAbsent(descriptor, new Set<String>());
                             descriptiveWordsMap.get(descriptor).add(word);
                         });
    }

    private Map<String, Set<String>> createDescriptiveWordsMap(String[] words) {
        Map<String, Set<String>> descriptiveWordsMap = new HashMap<String, Set<String>>();

        Arrays.stream(words)
              .forEach(word -> addWordToDescriptiveWordsMap(word, createDescriptorsOfWordList(word), descriptiveWordsMap));

        return descriptiveWordsMap;
    }

    private Map<String, Integer> createDescriptorScoreMap(Map<String, Set<String>> descriptiveWordsMap, String[] words) {
        return new HashMap<String, Integer>();
    }

    private List<String> createTopDescriptorsList(Map<String, Integer> descriptorScoreMap) {
        return new ArrayList<String>();
    }

}
