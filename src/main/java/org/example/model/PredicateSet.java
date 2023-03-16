package org.example.model;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class PredicateSet {
    BooleanOperator booleanOperator;
    List<String> terms;

    List<Set<Document>> documentSets;

//    Set<Document> processedDocuments;

    public PredicateSet() {
        this.terms = new ArrayList<String>();
        this.documentSets = new ArrayList<Set<Document>>();
//        this.processedDocuments = new HashSet<>();
    }

    public void addTerm(String newTerm) {
        terms.add(newTerm);
    }

    public void addDocumentSet(Set<Document> documentSet) {
        documentSets.add(documentSet);
    }

    public void setBooleanOperator(BooleanOperator booleanOperator) {
//        if(this.booleanOperator != null && this.booleanOperator != booleanOperator){
//            throw new WrongBooleanOperationException();
//        } else {
        this.booleanOperator = booleanOperator;
//        }
    }

    @Override
    public String toString() {
        return "PredicateSet{" +
                "booleanOperator=" + booleanOperator +
                ", terms=" + terms +
                '}';
    }

    public boolean isEmpty() {
        return terms.isEmpty();
    }
}