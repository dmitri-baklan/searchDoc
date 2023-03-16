package org.example.util;

public interface InputConstants {
    String LEFT_PARENTHESIS = "(";
    String RIGHT_PARENTHESIS = ")";
    String AMPERSAND = "&&";
    String VERTICAL_BAR = "||";
    String LETTERS_REGEX = "[a-zA-Z]+";
    String QUERY_REGEX = "[a-zA-Z|&()\\s]+";

    static boolean isLettersOnly(String str) {
        return str.matches(LETTERS_REGEX);
    }
}
