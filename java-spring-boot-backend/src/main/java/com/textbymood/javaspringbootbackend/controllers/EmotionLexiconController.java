package com.textbymood.javaspringbootbackend.controllers;

import java.io.File;

import com.textbymood.javaspringbootbackend.models.Lexicon;
import com.textbymood.javaspringbootbackend.models.TextAnalyzer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmotionLexiconController {

    private static final File emotionLexiconWordFile = new File("java-spring-boot-backend/src/main/resources/static/NRC-Emotion-Lexicon-Wordlevel-v0.92.txt");
    private static final Lexicon emotionLexicon = new Lexicon(emotionLexiconWordFile);
    private static final TextAnalyzer emotionTextAnalyzer = new TextAnalyzer(emotionLexicon);

    @GetMapping("/getEmotionLexicon")
    public Lexicon getEmotionVocabulary() {
        return emotionLexicon;
    } 
    

}
