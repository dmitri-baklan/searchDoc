package org.example.util;

import org.example.exception.InccorrectTermException;
import org.example.model.PredicateSet;

import static org.example.util.InputConstants.*;

public class QueryParser {
//    private static final String LEFT_PARENTHESIS = "(";
//    private static final String RIGHT_PARENTHESIS = ")";
//    private static final String AMPERSAND = "&&";
//    private static final String VERTICAL_BAR = "||";
//    private static final String LETTERS_REGEX = "[a-zA-Z]+";
//
//    private static final String QUERY_REGEX = "^[a-zA-Z|&()\\\\s]+$";
    PredicateQueryValidatorParser predicateQueryValidatorParser = new PredicateQueryValidatorParser();

//    public Query parseQuery(String predicateInput) throws IncorrectQueryInputException, InccorrectTermException {
//
//        String[] parsedString = predicateInput.split("\\s+");
//        queryValidator.validateQueryInput(predicateInput);
//
//        Query query = new Query();
//
//        PredicateSet predicateSet = new PredicateSet();
//        String currentString = "";
//        String previousString;
//
//        boolean leftParenthesisOpened = false;
//        BooleanOperator currentOperator = null;
//        BooleanOperator queryOperator = null;
//        BooleanOperator parenthesisOperator = null;
//        for (int i = 0; i < parsedString.length; i++) {
//            if (currentString.equals(LEFT_PARENTHESIS)) {
//                leftParenthesisOpened = true;
//            }
//            if (currentString.equals(AMPERSAND)) {
//            }
//            if (currentString.equals(VERTICAL_BAR)) {
//            }
//            if (currentString.equals(RIGHT_PARENTHESIS)) {
//            }
//        }
//    }

    void addTermToPredicateSet(String term, PredicateSet predicateSet) throws InccorrectTermException {
        if (isLettersOnly(term)) {
            predicateSet.addTerm(term);
        } else {
            throw new InccorrectTermException();
        }
    }
}
