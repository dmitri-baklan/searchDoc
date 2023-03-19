package org.example.util.vektorMode.impl;

import org.example.model.Document;
import org.example.model.Query;
import org.example.model.vektorMode.Vector;
import org.example.model.vektorMode.WordQuery;
import org.example.util.vektorMode.WeightCalculator;

import java.util.*;

public class CountIDFWeightCalculator implements WeightCalculator {
    Set<Document> documentSet;

    public CountIDFWeightCalculator(Set<Document> documentSet) {
        this.documentSet = documentSet;
    }

    @Override
    public Double countTermWeightInDocument(String term, Document document) {
        return null;
    }

    @Override
    public Double countTermWeightInQuery(String term, Query query) {
        return null;
    }

    @Override
    public org.example.model.vektorMode.Vector getDocumentVectorForTerms(Set<String> terms, Document document) {
        return new org.example.model.vektorMode.Vector(getTfIdfValues(terms, document.getWordsInFile()));
    }
    @Override
    public org.example.model.vektorMode.Vector getQueryVectorForTerms(Set<String> terms, Query query) {
        WordQuery wQuery = (WordQuery) query;
        return new Vector(getTfIdfValues(terms, wQuery.getWords()));
    }

    private Map<String, Double> getTfIdfValues(Set<String> terms, List<String> words){
        double frequencyCounter;
        double termIdfValue;
        Map<String, Double> tfIdfValue = new HashMap<>();
        for (String term : terms){
            frequencyCounter = getTfValue(term, words);
            termIdfValue = getTermIdfValue(term);
            tfIdfValue.put(term, frequencyCounter * termIdfValue);
        }
        return tfIdfValue;
    }

    private double getTfValue(String term, List<String> words){
        return Collections.frequency(words, term);
    }

    private double getTermIdfValue(String term){
        double documentsContainTerm = documentSet.stream().filter(d -> isDocumentContainTerm(term, d)).count();
        return Math.log(documentSet.size()/documentsContainTerm);
    }

    private boolean isDocumentContainTerm(String term, Document document){
        return document.getWordsInFile().stream().anyMatch(w -> w.equals(term));
    }

    @Override
    public void setDocumentSet(Set<Document> documentSet) {
        this.documentSet = documentSet;
    }
}
