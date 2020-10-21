package com.textbymood.javaspringbootbackend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
