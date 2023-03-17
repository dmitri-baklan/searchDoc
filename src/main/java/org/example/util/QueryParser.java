package org.example.util;

import org.example.exception.InccorrectTermException;
import org.example.model.PredicateSet;

import static org.example.util.InputConstants.*;

public class QueryParser {
    PredicateQueryValidatorParser predicateQueryValidatorParser = new PredicateQueryValidatorParser();

    void addTermToPredicateSet(String term, PredicateSet predicateSet) throws InccorrectTermException {
        if (isLettersOnly(term)) {
            predicateSet.addTerm(term);
        } else {
            throw new InccorrectTermException();
        }
    }
}
