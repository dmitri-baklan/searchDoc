package org.example.sevice.impl;

import org.example.exception.FolderPathException;
import org.example.model.Document;
import org.example.model.Query;
import org.example.model.vektorMode.DocumentWeight;
import org.example.model.vektorMode.Vector;
import org.example.sevice.DocumentRepresenter;
import org.example.util.vektorMode.SimilarityMeasure;
import org.example.util.vektorMode.WeightCalculator;
import org.example.util.vektorMode.impl.CosineSimilarityMeasure;
import org.example.util.vektorMode.impl.CountIDFWeightCalculator;

import java.util.*;
import java.util.stream.Collectors;

public class Algebraic extends DocumentRepresenter {
    Map<Document, Vector> documentVectors;
    WeightCalculator weightCalculator;
    SimilarityMeasure similarityMeasure;
    private static final double BOUNDARY_VALUE = 0;


    @Override
    public List<Document> getDocumetnsByQuery(Query query) {
        return getSortedDocuments(query);
    }

    private List<Document> getSortedDocuments(Query query) {
        Vector queryVector = weightCalculator.getQueryVectorForTerms(terms, query);
        List<DocumentWeight> weightedDocuments = getWeightedDocuments(queryVector);

        return sortDocumentsByWeight(filterDocumentsByWeight(weightedDocuments));
    }

    private List<DocumentWeight> getWeightedDocuments(Vector queryVector) {
        List<DocumentWeight> weightedDocuments = new ArrayList<>();
        Double similarityValue;
        for (Document doc : documentVectors.keySet()) {
            similarityValue = similarityMeasure.countSimilarityMeasure(queryVector, documentVectors.get(doc));
            DocumentWeight documentWeight = new DocumentWeight(doc, similarityValue);
            weightedDocuments.add(documentWeight);
        }
        return weightedDocuments;
    }

    private List<Document> sortDocumentsByWeight(List<DocumentWeight> weightedDocuments) {
        return weightedDocuments.stream()
                .sorted(Comparator.comparingDouble(DocumentWeight::getWeight).reversed())
                .collect(Collectors.toList());
    }

    private List<DocumentWeight> filterDocumentsByWeight(List<DocumentWeight> weightedDocuments) {
        return weightedDocuments.stream()
                .filter(d -> d.getWeight() > BOUNDARY_VALUE)
                .collect(Collectors.toList());
    }

    @Override
    public List<Document> addDocumentsFromFolder(String filesFolderPath) throws FolderPathException {
        documents = new HashSet<>(super.addDocumentsFromFolder(filesFolderPath));
        initVariables();
        setTermsFromDocuments(documents);
        setVectorForEachDocument();

        return new ArrayList<>(documents);
    }

    private void setTermsFromDocuments(Set<Document> documentSet) {
        for (Document doc : documentSet) {
            terms.addAll(doc.getWordsInFile());
        }
    }

    private void setVectorForEachDocument() {
        for (Document doc : documents) {
            Vector docVector = weightCalculator.getDocumentVectorForTerms(terms, doc);
            documentVectors.put(doc, docVector);
        }
    }

    private void initVariables() {
        terms = new HashSet<>();
        weightCalculator = new CountIDFWeightCalculator(documents);
        documentVectors = new HashMap<>();
        similarityMeasure = new CosineSimilarityMeasure();
    }
}
