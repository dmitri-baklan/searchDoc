package org.example.sevice.impl;

import org.example.model.*;
import org.example.model.booleanMode.BooleanOperator;
import org.example.model.booleanMode.PredicateQuery;
import org.example.model.booleanMode.PredicateSet;
import org.example.sevice.DocumentRepresenter;

import org.example.util.booleanMode.BooleanOperatorProcessor;

import java.util.*;

public class SetTheoretic extends DocumentRepresenter {

    @Override
    public List<Document> getDocumetnsByQuery(Query query) {
        PredicateQuery predicateQuery = (PredicateQuery) query;
        return new ArrayList<>(getDocumentsProcessed(predicateQuery));
    }

    private Set<Document> getDocumentsProcessed(PredicateQuery predicateQuery) {
        List<Set<Document>> documentSets = new ArrayList<>();
        for (PredicateSet ps : getPredicateSetProcessed(predicateQuery)) {
            documentSets.add(getDocumentsBooleanOperationProcessed(ps.getDocumentSets(), ps.getBooleanOperator()));
        }
        return getDocumentsBooleanOperationProcessed(documentSets, predicateQuery.getBooleanOperator());
    }

    private List<PredicateSet> getPredicateSetProcessed(PredicateQuery predicateQuery) {
        List<PredicateSet> predicateSets = new ArrayList<>();
        for (PredicateSet ps : predicateQuery.getPredicateSets()) {
            predicateSets.add(matchTermsWithDocuments(ps));
        }
        return predicateSets;
    }

    private Set<Document> getDocumentsBooleanOperationProcessed(List<Set<Document>> documentSets, BooleanOperator booleanOperator) {
        return BooleanOperatorProcessor.getBooleanOperationResult(
                documentSets, booleanOperator);
    }

    private PredicateSet matchTermsWithDocuments(PredicateSet ps) {
        Set<Document> docSet = new HashSet<>();
        for (String term : ps.getTerms()) {
            docSet = terms.isEmpty() ? getDocumentSetIncludesTerm(term) : addTermConsideringDefault(term);
            if (!docSet.isEmpty()) {
                ps.addDocumentSet(docSet);
            }
        }
        return ps;
    }

    private Set<Document> addTermConsideringDefault(String term) {
        if (terms.contains(term)) {
            return getDocumentSetIncludesTerm(term);
        }
        return Collections.emptySet();
    }

    private Set<Document> getDocumentSetIncludesTerm(String term) {
        Set<Document> docSet = new HashSet<>();
        for (Document doc : documents) {
            for (String word : doc.getWordsInFile()) {
                if (term.equals(word)) {
                    docSet.add(doc);
                    break;
                }
            }
        }
        return docSet;
    }


}
