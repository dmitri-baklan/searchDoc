package org.example;

import org.example.exception.TooMuchOptionsException;
import org.example.exception.UnknowInputException;
import org.example.model.Document;
import org.example.model.Query;
import org.example.sevice.DocumentRepresenter;
import org.example.sevice.impl.SetTheoretic;
import org.example.util.booleanMode.PredicateQueryValidatorParser;

import java.util.*;

import static org.example.util.MenuConstants.*;

public class Console {

    public static final int DEFAULT_OPTION = 0;
    public static final int OPTION_ONE = 1;
    public static final int OPTION_TWO = 2;
    public static final int OPTION_THREE = 3;
    public static final int OPTION_FOUR = 4;

    int userInputValue = DEFAULT_OPTION;

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
            System.out.println(ENTER_DEFAULT_TERMS);
            userInputValue = getUserInputValue();
            if(userInputValue == OPTION_ONE){
                addDefaultTerms();
            }
            addDocuments();
            if (userInputValue == OPTION_TWO) {
                break;
            }
            querySearch();
        }
    }

    private void addDefaultTerms(){
        boolean isIncorrectPath = true;
        while (isIncorrectPath) {
            System.out.println(ENTER_TERMS_PATH);
            try {
                Set<String> addedDocuments = dr.setTermsFromDocument(FOLDER_PREFIX + getUserInputString());
                addedDocuments.forEach(System.out::println);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            isIncorrectPath = false;
        }
    }

    private void addDocuments() throws UnknowInputException {
        System.out.println(DOCUMENTS_PATH_MENU);
        userInputValue = getUserInputValue();
        if (userInputValue == OPTION_ONE) {
            setPathToDocumentsFolder();
        } else if (userInputValue == OPTION_TWO) {
            return;
        } else {
            throw new UnknowInputException();
        }
    }

    private void querySearch() {
        while (userInputValue != OPTION_TWO && userInputValue != OPTION_THREE) {
            System.out.println(QUERY_SEARCH_MENU);
            userInputValue = getUserInputValue();
            if (userInputValue == OPTION_ONE) {
                Query query = getQueryFromInput();
                List<Document> documents = getDocuments(query);
                displayDocuments(documents);
            }
        }

    }

    private void setPathToDocumentsFolder() {
        boolean isIncorrect = true;
        while (isIncorrect) {
            System.out.println(ENTER_DOCUMENTS_PATH);
            try {
                List<Document> addedDocuments = dr.addDocumentsFromFolder(FOLDER_PREFIX + getUserInputString());
                displayDocuments(addedDocuments);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            isIncorrect = false;
        }

    }

    private void displayDocuments(List<Document> documents) {
        if(!documents.isEmpty()){
            for (Document doc : documents) {
                System.out.println(doc);
            }
        } else {
            System.out.println(THERE_IS_NO_DOCUMENTS);
        }
    }

    private List<Document> getDocuments(Query query) {
        List<Document> documents = new ArrayList<>();
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
        pQValidatorParser = new PredicateQueryValidatorParser();
        try {
            query = pQValidatorParser.validateQueryInput(userInputQuery);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return query;
    }

    private int getUserInputValue() {
        return Integer.parseInt(getUserInputString());
    }

    private String getUserInputString() {
        return scanner.nextLine();
    }

    private void printUserInputValue() {
        System.out.println(userInputValue);
    }
}
