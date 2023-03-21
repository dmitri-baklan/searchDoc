package org.example.util.vektorMode;

import org.example.model.Document;
import org.example.model.Query;
import org.example.model.vektorMode.Vector;

import java.util.Set;

public interface WeightCalculator {
    Vector getDocumentVectorForTerms(Set<String> terms, Document document);
    Vector getQueryVectorForTerms(Set<String> terms, Query query);
}
