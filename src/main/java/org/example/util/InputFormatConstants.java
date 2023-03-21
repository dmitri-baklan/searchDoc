package org.example.util;

public interface InputFormatConstants {
    String LEFT_PARENTHESIS = "(";
    String RIGHT_PARENTHESIS = ")";
    String AMPERSAND = "&&";
    String VERTICAL_BAR = "||";
    String LETTERS_REGEX = "[\\p{L}]+";
    String BOOLEAN_QUERY_REGEX = "[\\p{L}|&()\\s]+";
    String WORDS_QUERY_REGEX = "[\\p{L}\\s]+";

    static boolean isLettersOnly(String str) {
        return str.matches(LETTERS_REGEX);
    }
}
