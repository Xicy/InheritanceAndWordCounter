package com.xicy;

import java.util.Map;

/**
 * Created by Umut Akkaya on 7.05.2017.
 */
public class WordCounterTest {
    public static void main(String[] args) {
        WordCounter wc = new WordCounter("sample.txt");

        for (Map.Entry<String, Integer> entry : wc.getEntries().entrySet())
            System.out.print(entry.getKey() + "=" + entry.getValue() + ", ");
        System.out.println("\nTotal dis count:" + wc.getDistinctCount());
        System.out.println("Total word count" + wc.getTotalCount());
    }
}
