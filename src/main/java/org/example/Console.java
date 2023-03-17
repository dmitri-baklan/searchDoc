package org.example;

import org.example.exception.TooMuchOptionsException;
import org.example.exception.UnknowInputException;
import org.example.model.Document;
import org.example.model.DocumentWeight;
import org.example.model.Query;
import org.example.sevice.DocumentRepresenter;
import org.example.sevice.impl.SetTheoretic;
import org.example.util.PredicateQueryValidatorParser;

import javax.print.Doc;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

public class Console {

    public static final int DEFAULT_OPTION = 0;
    public static final int OPTION_ONE = 1;
    public static final int OPTION_TWO = 2;
    public static final int OPTION_THREE = 3;
    public static final int OPTION_FOUR = 4;

    int userInputValue = DEFAULT_OPTION;

    public static final String MAIN_MENU_TEXT = "Choose representing mode:\n" +
            "1. Set-theoretic\n" +
            "2. Algebraic\n" +
            "3. Quit\n";

    DocumentRepresenter dr;
    PredicateQueryValidatorParser pQValidatorParser;
    Scanner scanner = new Scanner(System.in);

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
        while (userInputValue != OPTION_THREE) {
            addDocuments();
            if(userInputValue == OPTION_TWO){
                break;
            }
            querySearch();
        }
    }

    private void addDocuments() throws UnknowInputException {
        System.out.println("1. Set path to documents folder.\n" +
                "2. Back to main menu\n");
        userInputValue = getUserInputValue();
        printUserInputValue();
        if (userInputValue == OPTION_ONE) {
            setPathToDocumentsFolder();
        } else if(userInputValue == OPTION_TWO){
            return;
        }
        else {
            throw new UnknowInputException();
        }
    }

    private void querySearch() {
        while (userInputValue != OPTION_TWO && userInputValue != OPTION_THREE) {
            System.out.println("1. Enter query.\n" +
                    "2. Back to previous step and set path to documents folder.\n" +
                    "3. Back to main menu.\n");
            userInputValue = getUserInputValue();
            if (userInputValue == OPTION_ONE) {
                Query query = getQueryFromInput();
                Set<Document> documents = getDocuments(query);
                displayDocuments(documents);
            }
        }

    }

    private void displayDocuments(Set<Document> documents) {
        for (Document doc : documents) {
            System.out.println(doc);
        }
    }


    private void setPathToDocumentsFolder() {
        boolean isIncorrect = true;
        while (isIncorrect) {
            System.out.println("Enter path to folder with documents:");
            try {
                dr.addDocumentsFromFolder(getUserInputString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            isIncorrect = false;
        }

    }

    private Set<Document> getDocuments(Query query) {
        Set<Document> documents = new HashSet<>();
        try {
            documents = dr.getDocumetnsByQuery(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return documents;
    }

    private Query getQueryFromInput() {
        String userInputQuery = getUserInputString();
        Query query = null;
        try {
            query = pQValidatorParser.validateQueryInput(userInputQuery);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return query;
    }

    private int getUserInputValue() {
        int in = 0;
        if (scanner.hasNext()) {
            in = scanner.nextInt();
        }
//        scanner.close();
        return in;
    }

    private String getUserInputString() {
        String in = "";
        if (scanner.hasNext()) {
            in = scanner.nextLine();
        }
//        scanner.close();
        return in;
    }

    private void printUserInputValue(){
        System.out.println(userInputValue);
    }
}
