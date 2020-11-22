package com.textbymood.javaspringbootbackend.controllers;

import java.io.File;

import com.textbymood.javaspringbootbackend.models.Lexicon;
import com.textbymood.javaspringbootbackend.models.TextAnalysisResult;
import com.textbymood.javaspringbootbackend.models.TextAnalyzer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmotionTextAnalyzerController {

    private static final File emotionLexiconWordFile = new File("java-spring-boot-backend/src/main/resources/static/NRC-Emotion-Lexicon-Wordlevel-v0.92.txt");
    private static final File stopWordsFile = new File("java-spring-boot-backend/src/main/resources/static/StopWords.txt");
    private static final Lexicon emotionLexicon = new Lexicon(emotionLexiconWordFile, stopWordsFile);
    private static final TextAnalyzer emotionTextAnalyzer = new TextAnalyzer(emotionLexicon);

    @GetMapping("/getEmotionLexicon")
    public Lexicon getEmotionLexicon() {
        return emotionLexicon;
    } 

    @PostMapping(path = "/analyzeText", consumes = "application/json", produces = "application/json")
    public TextAnalysisResult analyzeText(@RequestBody String text) {
        return emotionTextAnalyzer.analyzeText(text);
    }
}
