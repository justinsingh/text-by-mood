package com.textbymood.javaspringbootbackend.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TextAnalyzer.java --- A class for analyzing bodies of text to determine their relationships to a lexicon.
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
        String[] words = createArrayOfNonStopWords(text);

        Map<String, List<String>> descriptorToListOfWords = createDescriptorToListOfWordsMap(words);
        Map<String, Set<String>> descriptorToSetOfWords = createDescriptorToSetOfWordsMap(descriptorToListOfWords);
        Map<String, Double> descriptorToScore = createDescriptorToScoreMap(descriptorToListOfWords, words.length);
        List<String> topDescriptors = createTopDescriptorsList(descriptorToScore);
        
        return new TextAnalysisResult(text, topDescriptors, descriptorToScore, descriptorToSetOfWords);
    }

    private String[] createArrayOfNonStopWords(String text) {
        return Arrays.stream(text.split("\\s+"))
                     .map(word -> word.replaceAll("[^a-zA-Z ]", ""))
                     .map(word -> word.toLowerCase())
                     .filter(word -> !lexicon.getStopWords().contains(word))
                     .toArray(String[]::new);
    }

    private Map<String, List<String>> createDescriptorToListOfWordsMap(String[] words) {
        Map<String, List<String>> descriptorToListOfWords = new HashMap<String, List<String>>();

        Arrays.stream(words)
              .forEach(word -> addWord(word, createDescriptorsOfWordList(word), descriptorToListOfWords));

        return descriptorToListOfWords;
    }

    private void addWord(String word, List<String> descriptorsOfWord, Map<String, List<String>> descriptiveWordsMap) {
        descriptorsOfWord.stream()
                         .forEach(descriptor -> {
                             descriptiveWordsMap.putIfAbsent(descriptor, new ArrayList<String>());
                             descriptiveWordsMap.get(descriptor).add(word);
                         });
    }

    private List<String> createDescriptorsOfWordList(String word) {
        return vocabulary.keySet().stream()
                                  .filter(descriptor -> vocabulary.get(descriptor).contains(word))
                                  .collect(Collectors.toList());
    }

    private Map<String, Set<String>> createDescriptorToSetOfWordsMap(Map<String, List<String>> descriptorToListOfWords) {
        Map<String, Set<String>> descriptorToSetOfWords = new HashMap<String, Set<String>>();

        for (String descriptor: descriptorToListOfWords.keySet()) {
            descriptorToSetOfWords.put(descriptor, new HashSet<String>(descriptorToListOfWords.get(descriptor)));
        }

        return descriptorToSetOfWords;
    }

    private Map<String, Double> createDescriptorToScoreMap(Map<String, List<String>> descriptorToListOfWords, int numOfWords) {
        Map<String, Double> descriptorToScore = new HashMap<String, Double>();

        for (String descriptor: descriptorToListOfWords.keySet()) {
            double listOfWordsLength = descriptorToListOfWords.get(descriptor).size();
            descriptorToScore.put(descriptor, (listOfWordsLength / numOfWords) * 100);
        }

        return descriptorToScore;
    }

    private List<String> createTopDescriptorsList(Map<String, Double> descriptorToScore) {
        Optional<Double> highestDescriptorScoreOptional = descriptorToScore.values().stream()
                                                               .max((num1, num2) -> num1.compareTo(num2));
        double highestDescriptorScore = highestDescriptorScoreOptional.orElse(-1.0);

        return descriptorToScore.keySet().stream()
                                .filter(descriptor -> descriptorToScore.get(descriptor) == highestDescriptorScore)
                                .collect(Collectors.toList());
    }

}
