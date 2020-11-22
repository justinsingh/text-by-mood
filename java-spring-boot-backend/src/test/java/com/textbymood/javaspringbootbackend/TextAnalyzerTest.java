package com.textbymood.javaspringbootbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.textbymood.javaspringbootbackend.models.Lexicon;
import com.textbymood.javaspringbootbackend.models.TextAnalysisResult;
import com.textbymood.javaspringbootbackend.models.TextAnalyzer;

import org.junit.jupiter.api.Test;

public class TextAnalyzerTest {
    File wordFile = new File("src/test/java/com/textbymood/javaspringbootbackend/LexiconTextTest.txt"); 
    File stopWordFile = new File("src/test/java/com/textbymood/javaspringbootbackend/LexiconStopWordsTest.txt");  
    Lexicon lexicon = new Lexicon(wordFile, stopWordFile);
    TextAnalyzer textAnalyzer = new TextAnalyzer(lexicon);

    @Test
    void analyzeTextTest() {
        String text = "Abandon or abba!";
        List<String> topDescriptors = new ArrayList<String>();
        topDescriptors.add("fear");
        topDescriptors.add("positive");

        Map<String, Double> descriptorToScore = new HashMap<String, Double>();
        descriptorToScore.put("fear", 50.0);
        descriptorToScore.put("positive", 50.0);

        Set<String> fearSetOfWords = new HashSet<String>();
        fearSetOfWords.add("abandon");
        Set<String> positiveSetOfWords = new HashSet<String>();
        positiveSetOfWords.add("abba");
        
        Map<String, Set<String>> descriptorToSetOfWords = new HashMap<String, Set<String>>();
        descriptorToSetOfWords.put("fear", fearSetOfWords);
        descriptorToSetOfWords.put("positive", positiveSetOfWords);

        TextAnalysisResult testResult = new TextAnalysisResult(text, topDescriptors, descriptorToScore, descriptorToSetOfWords);
        TextAnalysisResult testResult2 = textAnalyzer.analyzeText(text);

        assertTrue(testResult.equals(testResult2));
    }
}
