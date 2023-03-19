package org.example.util.vektorMode;

import org.example.model.Document;
import org.example.model.Query;
import org.example.model.vektorMode.Vector;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface WeightCalculator {
    Double countTermWeightInDocument(String term, Document document);
    Double countTermWeightInQuery(String term, Query query);
    Vector getDocumentVectorForTerms(Set<String> terms, Document document);
    Vector getQueryVectorForTerms(Set<String> terms, Query query);
//    Map<String, Float> getDocumentWeightMapForTerms(List<String> terms, Document document);
//    Map<String, Float> getQueryWeightMapForTerms(List<String> terms, Query query);

    void setDocumentSet(Set<Document> documentSet);
}
