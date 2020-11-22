package com.textbymood.javaspringbootbackend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Map;
import java.util.Set;

import com.textbymood.javaspringbootbackend.models.Lexicon;

public class LexiconTest {
    Lexicon lexicon = new Lexicon();
    Map<String, Set<String>> vocabulary = lexicon.getVocabulary();
    Set<String> stopWords = lexicon.getStopWords();

    @Test
    void testAddingWord() {
        lexicon.addWord("abruptly", "surprise");
        assertTrue(vocabulary.get("surprise").contains("abruptly"));
        assertFalse(lexicon.addWord("abruptly", "surprise"));
    }

    @Test
    void testAddingWordsFromFile() {
        File wordFile = new File("src/test/java/com/textbymood/javaspringbootbackend/LexiconTextTest.txt");
        lexicon.addWordsFromFile(wordFile);

        assertTrue(vocabulary.get("fear").contains("abandon"));
        assertTrue(vocabulary.get("fear").contains("abandonment"));
        assertFalse(vocabulary.get("fear").contains("abacus"));
        assertTrue(vocabulary.get("positive").contains("abba"));
        assertFalse(vocabulary.get("positive").contains("abhorrent"));
    }

    @Test
    void testAddingStopWord() {
        lexicon.addStopWord("and");
        assertTrue(stopWords.contains("and"));
        assertFalse(stopWords.contains("negative"));
    }

    @Test
    void testAddingStopWordsFromFile() {
        File stopWordFile = new File("src/test/java/com/textbymood/javaspringbootbackend/LexiconStopWordsTest.txt"); 
        lexicon.addStopWordsFromFile(stopWordFile);

        assertTrue(stopWords.contains("or"));
        assertTrue(stopWords.contains("have"));
        assertFalse(stopWords.contains("positive"));
    }
}
