package com.textbymood.javaspringbootbackend.models;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Result {
    final List<String> topDescriptors;
    final Map<String, Integer> descriptorScoreMap;
    final Map<String, Set<String>> descriptorSearchWords;

    public Result(List<String> topDescriptors, Map<String, Integer> descriptorScoreMap, Map<String, Set<String>> descriptorSearchWords) {
        this.topDescriptors = topDescriptors;
        this.descriptorScoreMap = descriptorScoreMap;
        this.descriptorSearchWords = descriptorSearchWords;
    }
}
