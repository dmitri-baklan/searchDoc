package org.example.model.booleanMode;

import lombok.Getter;
import org.example.model.Document;

import java.util.*;

@Getter
public class PredicateSet {
    BooleanOperator booleanOperator;
    List<String> terms;

    List<Set<Document>> documentSets;

    public PredicateSet() {
        this.terms = new ArrayList<String>();
        this.documentSets = new ArrayList<Set<Document>>();
    }

    public void addTerm(String newTerm) {
        terms.add(newTerm);
    }

    public void addDocumentSet(Set<Document> documentSet) {
        documentSets.add(documentSet);
    }

    public void setBooleanOperator(BooleanOperator booleanOperator) {
        this.booleanOperator = booleanOperator;
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
