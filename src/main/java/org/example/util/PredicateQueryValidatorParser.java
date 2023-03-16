package org.example.util;

import org.example.exception.IncorrectQueryInputException;
import org.example.exception.WrongBooleanOperationException;
import org.example.model.BooleanOperator;
import org.example.model.PredicateQuery;
import org.example.model.PredicateSet;

import static org.example.util.InputConstants.*;

public class PredicateQueryValidatorParser {
    private String prevStr = "";
    private String currentString = "";
    private String nextStr = "";
    private boolean leftParenthesisOpened = false;
    private BooleanOperator currentOperator = null;
    private BooleanOperator queryOperator = null;
    private BooleanOperator parenthesisOperator = null;
    private PredicateSet predicateSet = null;
    PredicateQuery predicateQuery = new PredicateQuery();
    String[] splittedString;

    public PredicateQuery validateQueryInput(String predicateInput) throws IncorrectQueryInputException, WrongBooleanOperationException {
        if (!predicateInput.matches(QUERY_REGEX)) {
            throw new IncorrectQueryInputException("Unknown symbols, only letters and next characters is allowed: '(', ')', '&', '|'.");
        }
        splittedString = predicateInput.split("\\s+");
        return validateParenthesisAndBooleanOperators(splittedString);
    }

    private PredicateQuery validateParenthesisAndBooleanOperators(String[] splittedString) throws IncorrectQueryInputException,
            WrongBooleanOperationException {


        for (int i = 0; i < splittedString.length; i++) {
            setDefaultsStringIteration(i);
            checkLeftParenthesisCase();
            checkRightParenthesisCase();
            checkAmpersandCase();
            checkVerticalBarCase();
            checkTermCase();
            checkIncorrectSymbol();
        }
        setQueryDataAfterIterations();
        return predicateQuery;
    }

    private void checkIncorrectSymbol()throws IncorrectQueryInputException {
        if(!currentString.equals(AMPERSAND) && !currentString.equals(VERTICAL_BAR)
                && !currentString.isEmpty() && !currentString.equals(LEFT_PARENTHESIS)
                && !currentString.equals(RIGHT_PARENTHESIS) && !isLettersOnly(currentString)){
            throw new IncorrectQueryInputException("Unknown symbol exception!");
        }
    }

    private void setDefaultsStringIteration(int i) {
        prevStr = currentString;
        currentString = splittedString[i];
        if (i + 1 < splittedString.length) {
            nextStr = splittedString[i + 1];
        } else {
            nextStr = "";
        }
    }

    private void setQueryDataAfterIterations()throws IncorrectQueryInputException {
        if(leftParenthesisOpened){
            throw new IncorrectQueryInputException("Parenthesis wasn't closed!");
        }
        if (!predicateSet.isEmpty()) {
            predicateQuery.addPredicateSet(predicateSet);
        }
        if (queryOperator != null) {
            predicateQuery.setBooleanOperator(queryOperator);
        }
    }

    private void checkLeftParenthesisCase() throws IncorrectQueryInputException {
        if (currentString.equals(LEFT_PARENTHESIS)) {
            if (predicateSet != null && !predicateSet.isEmpty()) {
                predicateQuery.addPredicateSet(predicateSet);
            }
            predicateSet = new PredicateSet();
            processLeftParenthesis();
            leftParenthesisOpened = true;
        }
    }

    private void checkRightParenthesisCase() throws IncorrectQueryInputException {
        if (currentString.equals(RIGHT_PARENTHESIS)) {
            if (predicateSet != null && !predicateSet.isEmpty()) {
                predicateQuery.addPredicateSet(predicateSet);
            }
            predicateSet = new PredicateSet();
            if (leftParenthesisOpened) {
                checkRightParenthesis();
                leftParenthesisOpened = false;

                if (nextStr.isEmpty() && queryOperator == null) {
                    queryOperator = parenthesisOperator;
                }
                parenthesisOperator = null;
            } else {
                throw new IncorrectQueryInputException("Left PARENTHESIS isn't opened!");
            }
        }
    }

    private void checkAmpersandCase() throws IncorrectQueryInputException {
        if (currentString.equals(AMPERSAND)) {
            currentOperator = BooleanOperator.AND;
            processBooleanOperator();
        }
    }

    private void checkVerticalBarCase() throws IncorrectQueryInputException {
        if (currentString.equals(VERTICAL_BAR)) {
            currentOperator = BooleanOperator.OR;
            processBooleanOperator();
        }
    }

    private void checkTermCase() throws WrongBooleanOperationException {
        if (isLettersOnly(currentString)) {
            predicateSet = (predicateSet == null) ? new PredicateSet() : predicateSet;
            addTermToPredicateSet(leftParenthesisOpened ? parenthesisOperator : queryOperator);
            predicateSet.addTerm(currentString);
        }
    }

    private void addTermToPredicateSet(BooleanOperator booleanOperator) throws WrongBooleanOperationException {
        predicateSet.setBooleanOperator(booleanOperator == null ? chooseBooleanOperator() : booleanOperator);
    }

    private void processLeftParenthesis() throws IncorrectQueryInputException {
        if (leftParenthesisOpened) {
            throw new IncorrectQueryInputException("Left parenthesis is already opened!");
        } else {
            checkLeftParenthesis();
        }
    }

    private void checkLeftParenthesis() throws IncorrectQueryInputException {
        if (!isLeftParenthesisPlacementCorrect()) {
            throw new IncorrectQueryInputException("Left parenthesis location exception!");
        }
    }

    private void checkRightParenthesis() throws IncorrectQueryInputException {
        if (!isRightParenthesisPlacementCorrect(prevStr, nextStr)) {
            throw new IncorrectQueryInputException("Right parenthesis location exception!");
        }
    }

    private boolean isLeftParenthesisPlacementCorrect() {
        return (prevStr.isEmpty() || prevStr.equals(AMPERSAND) || prevStr.equals(VERTICAL_BAR)) &&
                (isLettersOnly(nextStr));
    }

    private boolean isRightParenthesisPlacementCorrect(String prevStr, String nextStr) {
        return isLettersOnly(prevStr) && (nextStr.isEmpty() || nextStr.equals(AMPERSAND) || nextStr.equals(VERTICAL_BAR));
    }

    private void processBooleanOperator() throws IncorrectQueryInputException {
        if (leftParenthesisOpened) {
            parenthesisOperator = setBooleanOperatorIfNull(currentOperator, parenthesisOperator);
            checkBooleanOperator(parenthesisOperator, currentOperator);
        } else {
            queryOperator = setBooleanOperatorIfNull(currentOperator, queryOperator);
            checkBooleanOperator(queryOperator, currentOperator);
        }
    }

    private void checkBooleanOperator(BooleanOperator correctOperator, BooleanOperator currentOperator) throws IncorrectQueryInputException {
        if ((correctOperator != currentOperator) ||
                !isBooleanOperatorPlacementIsCorrect()) {
            throw new IncorrectQueryInputException("Boolean operator error!");
        }
    }

    private BooleanOperator setBooleanOperatorIfNull(BooleanOperator in, BooleanOperator out) {
        if (out == null) {
            return in;
        }
        return out;
    }

    private boolean isBooleanOperatorPlacementIsCorrect() {
        return (isLettersOnly(prevStr) || prevStr.equals(RIGHT_PARENTHESIS))
                && (isLettersOnly(nextStr) || nextStr.equals(LEFT_PARENTHESIS));
    }

    private BooleanOperator chooseBooleanOperator() {
        return nextStr.equals(AMPERSAND) ? BooleanOperator.AND : BooleanOperator.OR;
    }


}
