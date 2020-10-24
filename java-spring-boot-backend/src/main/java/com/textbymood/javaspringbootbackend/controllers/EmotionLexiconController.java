package com.textbymood.javaspringbootbackend.controllers;

import java.io.File;
import java.util.Map;
import java.util.Set;

import com.textbymood.javaspringbootbackend.models.Lexicon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmotionLexiconController {

    private static final File emotionLexiconWordFile = new File("java-spring-boot-backend/src/main/resources/static/NRC-Emotion-Lexicon-Wordlevel-v0.92.txt");
    private static final Lexicon EmotionLexicon = new Lexicon(emotionLexiconWordFile);
    private static final Map<String, Set<String>> emotionVocabulary = EmotionLexicon.getVocabulary();

    @GetMapping("/getEmotionVocabulary")
    public Map<String, Set<String>> getEmotionVocabulary() {
        return emotionVocabulary;
    } 
    
}
