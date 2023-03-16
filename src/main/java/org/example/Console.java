package org.example;

import org.example.exception.TooMuchOptionsException;
import org.example.exception.UnknowInputException;
import org.example.sevice.DocumentRepresenter;
import org.example.sevice.impl.SetTheoretic;

import java.util.Scanner;

public class Console {

    public static final int DEFAULT_OPTION = 0;
    public static final int OPTION_ONE = 1;
    public static final int OPTION_TWO = 2;
    public static final int OPTION_THREE = 3;
    public static final int OPTION_FOUR = 4;

    int userInputValue = DEFAULT_OPTION;

    public static final String MAIN_MENU_TEXT = "Choose representing mode:\n" +
            "1. Set-theoretic\n" +
            "2. Algebraic" +
            "3. Quit";

    DocumentRepresenter dr;

    Console() {
    }

    public void runMainMenu() {
        try {
            mainMenuOperation();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void mainMenuOperation() throws UnknowInputException, TooMuchOptionsException {

        while (userInputValue != OPTION_THREE) {
            System.out.println(MAIN_MENU_TEXT);

            userInputValue = getUserInputValue();
            if (userInputValue == OPTION_ONE) {
                setTheoreticMenu();
            } else if (userInputValue == OPTION_TWO) {

            } else {
                throw new UnknowInputException();
            }
        }
    }

    private void setTheoreticMenu() throws UnknowInputException {
        dr = new SetTheoretic();
        addDocuments();
    }

    private void addDocuments() throws UnknowInputException {
        System.out.println("1. Set path to documents folder.");
        userInputValue = getUserInputValue();
        if (userInputValue == OPTION_ONE) {
            setPathToDocumentsFolder();
        } else {
            throw new UnknowInputException();
        }
    }

    private void manuallyAddingDocuments() {

    }

    private void setPathToDocumentsFolder() {

    }

    private int getUserInputValue() {
        Scanner scanner = new Scanner(System.in);
        int in = 0;
        if (scanner.hasNext()) {
            in = scanner.nextInt();
        }
        scanner.close();
        return in;
    }

    private String getUserInputString() {
        Scanner scanner = new Scanner(System.in);
        String in = "";
        if (scanner.hasNext()) {
            in = scanner.nextLine();
        }
        scanner.close();
        return in;
    }
}
