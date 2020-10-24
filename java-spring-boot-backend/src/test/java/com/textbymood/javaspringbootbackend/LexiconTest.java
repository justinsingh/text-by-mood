package com.textbymood.javaspringbootbackend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Map;
import java.util.Set;

import com.textbymood.javaspringbootbackend.models.Lexicon;

public class LexiconTest {
    Lexicon lexicon = new Lexicon(); 
    Map<String, Set<String>> vocabulary = lexicon.getVocabulary();

    @Test
    void testAddingWord() {
        lexicon.addWord("abruptly", "surprise");
        assertEquals(true, vocabulary.get("surprise").contains("abruptly"));

        assertEquals(false, lexicon.addWord("abruptly", "surprise"));
    }

    @Test
    void testAddingWordsFromFile() {
        File testFile = new File("src/test/java/com/textbymood/javaspringbootbackend/LexiconTextTest.txt");
        lexicon.addWordsFromFile(testFile); 

        assertEquals(true, vocabulary.get("fear").contains("abandon"));
        assertEquals(true, vocabulary.get("fear").contains("abandonment"));
        assertEquals(false, vocabulary.get("fear").contains("abacus"));
        assertEquals(true, vocabulary.get("positive").contains("abba"));
        assertEquals(false, vocabulary.get("positive").contains("abhorrent"));
    }
}
