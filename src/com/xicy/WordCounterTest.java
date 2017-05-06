package com.xicy;

import java.util.Map;

/**
 * Created by Umut Akkaya on 7.05.2017.
 */
public class WordCounterTest {
    public static void main(String[] args) {
        WordCounter wc = new WordCounter();
        wc.initialize("So close no matter how far \n Couldn't be much more from the heart \n Forever trusting who we are \n And nothing else matters \n Never opened myself this way \n Life is outs we live it our way \n All these words I don't just say \n And nothing else matters \n\nTrust I seek and I find in you \n Every day for us something new \n Open mind for a different view \n And nothing else matters \n \n Never cared for what they do \n Never cared for what they know\n But I know");

        for (Map.Entry<String, Integer> entry : wc.getEntries().entrySet())
            System.out.print(entry.getKey() + "=" + entry.getValue() + ", ");
        System.out.println("\n" + wc.getDistinctCount());
        System.out.println(wc.getTotalCount());
    }
}
