package com.textbymood.javaspringbootbackend.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Lexicon.java --- A class for representing the vocabulary of a language, including information on each item in it.
 * 
 * @author Justin Singh
 */
public class Lexicon {
    /**
     * The key of this HashMap represents a descriptor for words.
     * The value of this HashMap represents a Set of words being described.
     */
    private Map<String, Set<String>> vocabulary = new HashMap<String, Set<String>>();
    /**
     * Stop words are words which have no meaning in the vocabulary of a lexicon.
     */
    private Set<String> stopWords = new HashSet<String>();
    
    public Lexicon() {

    }

    /**
     * Creates a new Lexicon object including the vocabulary and stop words contained in the given files.
     * @param wordFile A file containing words and their descriptors
     * @param stopWordsFile A file contianing stop words
     * @see #addWordsFromFile(File)
     */
    public Lexicon(File wordFile, File stopWordFile) {
        addWordsFromFile(wordFile);
        addStopWordsFromFile(stopWordFile);
    }

    public Lexicon(File wordFile) {
        addWordsFromFile(wordFile);
    }

    /**
     * Add a given word to the vocabulary with association to its given descriptor.
     * @param word A word being described
     * @param descriptor A describing word 
     * @return Boolean resulting in true if the given word is not already contained in the given descriptor's set. 
     *         Returns false if the given word is already contained in the given descriptor's set.
     */
    public boolean addWord(String word, String descriptor) {
        vocabulary.putIfAbsent(descriptor, new HashSet<String>());

        Set<String> descriptorSet = vocabulary.get(descriptor);
        if (descriptorSet.contains(word)) {
            return false;
        }
        else {
            descriptorSet.add(word);
            return true;
        }
    }

    /**
     * Add a given stop word to the stopWords set.
     * @param stopWord A word with no meaning to the vocabulary of a lexicon
     * @return Boolean resulting to true if the given stop word is not already contained in the stopWords set.
     *         Returns false if the given stop word is already contained in the stopWords set.
     */
    public boolean addStopWord(String stopWord) {
        return stopWords.add(stopWord);
    }

    /**
     * Add multiple words from a file to the vocabulary with assocation to their descriptors.
     * Each line of the file must be in the format of: "[word] [descriptor] [binary 0 or 1, unassociated or associated]"
     * Format derived from file format of the NRC Emotion Lexicon
     * @param wordFile
     * @see #addWord(String, String)
     */
    public void addWordsFromFile(File wordFile) {
        try (Stream<String> wordFileStream = Files.lines(wordFile.toPath())) {
            wordFileStream.map(lineOfText -> lineOfText.split("\\s+"))
                          .filter(lineOfTextArr -> lineOfTextArr.length == 3 && lineOfTextArr[2].equals("1"))
                          .forEach(lineOfTextArr -> addWord(lineOfTextArr[0], lineOfTextArr[1])); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add multiple stop words from a file to the stopWords set.
     * @param stopWordFile
     * @see #addStopWord(String)
     */
    public void addStopWordsFromFile(File stopWordFile) {
        try (Stream<String> stopWordFileStream = Files.lines(stopWordFile.toPath())) {
            stopWordFileStream.forEach(stopWord -> addStopWord(stopWord));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Set<String>> getVocabulary() {
        return vocabulary;
    }

    public Set<String> getStopWords() {
        return stopWords;
    }
}
