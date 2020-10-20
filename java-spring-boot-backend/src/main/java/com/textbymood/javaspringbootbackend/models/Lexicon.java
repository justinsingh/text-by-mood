package com.textbymood.javaspringbootbackend.models;

import java.io.File;
import java.io.FileInputStream;
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
    
    public Lexicon() {

    }

    /**
     * Creates a new Lexicon object including the vocabulary contained in the given file.
     * @param wordFile A file containing words and their descriptors
     * @see #addWordsFromFile(File)
     */
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
     * Add multiple words from a file to the vocabulary with assocation to their descriptors.
     * Each line of the file must be in the format of: "[word] [descriptor]"
     * @param wordFile
     * @see #addWord(String, String)
     */
    public void addWordsFromFile(File wordFile) {
        try (Stream<String> wordFileStream = Files.lines(wordFile.toPath())) {
            wordFileStream.forEach(lineOfText -> {
                String[] lineOfTextArr = lineOfText.split(" ");
                String word = lineOfTextArr[0];
                String descriptor = lineOfTextArr[1];

                addWord(word, descriptor);
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
