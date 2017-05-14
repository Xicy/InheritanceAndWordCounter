package com.xicy;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Map;

/**
 * Created by Umut Akkaya on 7.05.2017.
 */
public class WordCounterTest {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Sample Text File", "txt", "text"));
        int result;

        do {
            if ((result = fileChooser.showOpenDialog(null)) == JFileChooser.APPROVE_OPTION) {
                WordCounter wc = new WordCounter(fileChooser.getSelectedFile().getAbsolutePath());
                for (Map.Entry<String, Integer> entry : wc.getEntries().entrySet())
                    System.out.print(entry.getKey() + "=" + entry.getValue() + ", ");
                System.out.println("\nTotal dis count:" + wc.getDistinctCount());
                System.out.println("Total word count" + wc.getTotalCount());
            }
        } while (result != JFileChooser.APPROVE_OPTION);

    }
}
