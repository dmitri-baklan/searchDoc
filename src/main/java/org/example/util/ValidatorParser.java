package org.example.util;

import org.example.exception.IncorrectQueryInputException;
import org.example.exception.WrongBooleanOperationException;
import org.example.model.Query;
import org.example.model.booleanMode.PredicateQuery;

public interface ValidatorParser {
    Query validateQueryInput(String predicateInput) throws IncorrectQueryInputException;
}
