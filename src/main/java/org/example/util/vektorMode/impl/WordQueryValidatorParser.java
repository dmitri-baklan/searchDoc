package org.example.util.vektorMode.impl;

import org.example.exception.IncorrectQueryInputException;
import org.example.model.Query;
import org.example.model.vektorMode.WordQuery;
import org.example.util.ValidatorParser;

import java.util.List;
import static org.example.util.InputFormatConstants.WORDS_QUERY_REGEX;

public class WordQueryValidatorParser implements ValidatorParser {
    @Override
    public Query validateQueryInput(String userInput) throws IncorrectQueryInputException {
        if (!userInput.matches(WORDS_QUERY_REGEX)) {
            throw new IncorrectQueryInputException("User input should contain only letters and spaces!");
        }
        String[] splittedString = userInput.toLowerCase().split("\\s+");
        return convertInputToQuery(splittedString);
    }

    private Query convertInputToQuery(String[] words) {
        return new WordQuery(List.of(words));
    }
}
