package org.example.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public static List<String> getAllWordsFromFile(String pathToFile){
        List<String> words = new ArrayList<>();
        try {
            words = writeWordsFromFileToList(pathToFile, words);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return words;
    }

    private static List<String> writeWordsFromFileToList(String pathToFile, List<String> words) throws FileNotFoundException{
        File file = new File(pathToFile);
        Scanner scanner = new Scanner(file);
        String word;
        String formattedWord;
        while (scanner.hasNext()) {
            word = scanner.next();
            formattedWord = formatWord(word);
            if (!formattedWord.isEmpty()) {
                words.add(formattedWord);
            }
        }
        scanner.close();
        return words;
    }

    private static String formatWord(String word){
        return replacePunctuationMarks(word).toLowerCase();
    }

    private static String replacePunctuationMarks(String word){
        return word.replaceAll("[!\".,:;?\\-\\(\\)\\<\\>\\[\\]]", "");
    }
}
