package org.example.util;

import org.example.exception.IncorrectQueryInputException;
import org.example.model.Query;

public interface ValidatorParser {
    Query validateQueryInput(String userInput) throws IncorrectQueryInputException;
}
