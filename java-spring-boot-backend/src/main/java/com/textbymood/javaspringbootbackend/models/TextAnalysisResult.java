package com.textbymood.javaspringbootbackend.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Result.java --- A class for representing the relationship between a body of text and a lexicon.
 * 
 * @author Justin Singh
 */
public class TextAnalysisResult {
    public final String text;
    public final List<String> topDescriptors;
    public final Map<String, Double> descriptorToScore;
    public final Map<String, Set<String>> descriptorToSetOfWords;

    public TextAnalysisResult(String text, List<String> topDescriptors, Map<String, Double> descriptorToScore, Map<String, Set<String>> descriptorToSetOfWords) {
        this.text = text;
        this.topDescriptors = topDescriptors;
        this.descriptorToScore = descriptorToScore;
        this.descriptorToSetOfWords = descriptorToSetOfWords;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (!(otherObject instanceof TextAnalysisResult))
            return false;
        
        TextAnalysisResult otherTextAnalysisResult = (TextAnalysisResult)otherObject;

        return (equalLists(this.topDescriptors, otherTextAnalysisResult.topDescriptors) &&
                this.descriptorToScore.equals(otherTextAnalysisResult.descriptorToScore) &&
                this.descriptorToSetOfWords.equals(otherTextAnalysisResult.descriptorToSetOfWords));
    }

    private boolean equalLists(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size())
            return false;

            list1 = new ArrayList<String>(list1);
            list2 = new ArrayList<String>(list2);

            Collections.sort(list1);
            Collections.sort(list2);
            return list1.equals(list2);
    }
}
