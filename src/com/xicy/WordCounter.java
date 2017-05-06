package com.xicy;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Umut Akkaya on 6.05.2017.
 */
public class WordCounter {
    private HashMap<String, Integer> set;
    private int totalCount;

    public WordCounter() {
        reset();
    }

    public void initialize(String text) {
        if (set.size() > 0) throw new ExceptionInInitializerError("use reset method");
        for (String word : text.replaceAll("\n", "").toLowerCase().split("\\s")) {
            word = word.trim();
            if (!word.isEmpty()) {
                set.put(word, set.getOrDefault(word, 0) + 1);
                totalCount++;
            }
        }
    }

    public void reset() {
        set = new HashMap<>();
        totalCount = 0;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getDistinctCount() {
        return set.size();
    }

    public Map<String, Integer> getEntries() {
        if (set.size() == 0) throw new ExceptionInInitializerError("use initialize method");
        return new TreeMap<String, Integer>(set);
    }
}
