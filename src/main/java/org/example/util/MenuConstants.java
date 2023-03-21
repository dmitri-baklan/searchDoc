package org.example.util;

public interface MenuConstants {
    int DEFAULT_OPTION = 0;
    int OPTION_ONE = 1;
    int OPTION_TWO = 2;
    int OPTION_THREE = 3;
    int OPTION_FOUR = 4;
    String FOLDER_PREFIX = "src/main/resources/";
    String MAIN_MENU_TEXT = "Choose representing mode:\n" +
            "1. Set-theoretic\n" +
            "2. Algebraic\n" +
            "3. Quit\n";
    String DOCUMENTS_PATH_MENU = "1. Set path to documents folder.\n" +
            "2. Back to main menu\n";
    String QUERY_SEARCH_MENU = "1. Enter query.\n" +
            "2. Back to previous step and set path to documents folder.\n" +
            "3. Back to main menu.\n";
    String ENTER_DOCUMENTS_PATH = "Enter path to folder with documents:";
    String ENTER_TERMS_PATH = "Enter path to file with default terms:";
    String THERE_IS_NO_DOCUMENTS = "There is no documents that matches the query";
    String ENTER_DEFAULT_TERMS = "1. Enter path to file with default terms.\n" +
            "2. Skip adding default terms stage";

    String BOOLEAN_QUERY_TEMPLATE = "Query template: A && ( B || C ) && D";
    String WORD_QUERY_TEMPLATE = "Query template: A B C D";
}