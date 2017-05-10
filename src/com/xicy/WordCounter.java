package com.xicy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Umut Akkaya on 6.05.2017.
 */
public class WordCounter {
    private HashMap<String, Integer> set;
    private int totalCount;

    public WordCounter(String path) {
        set = new HashMap<>();
        totalCount = 0;
        try {
            for (String word : new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8).replaceAll("\n", "").toLowerCase().split("\\s")) {
                word = word.trim();
                if (!word.isEmpty()) {
                    set.put(word, set.getOrDefault(word, 0) + 1);
                    totalCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
