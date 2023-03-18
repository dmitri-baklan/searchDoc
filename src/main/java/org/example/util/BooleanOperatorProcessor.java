package org.example.util;

import org.example.model.BooleanOperator;
import org.example.model.Document;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BooleanOperatorProcessor {

    public static Set<Document> getBooleanOperationResult(List<Set<Document>> documentSets, BooleanOperator booleanOperator){
        return booleanOperator.equals(BooleanOperator.OR)
                ? processUnionOperation(documentSets) : processIntersectionOperation(documentSets);
    }

    private static Set<Document> processUnionOperation(List<Set<Document>> documentSets){
        return documentSets.stream().flatMap(Collection::stream).collect(Collectors.toSet());
    }

    private static Set<Document> processIntersectionOperation(List<Set<Document>> documentSets){
        Set<Document> resultSet = new HashSet<>();
        for (Document doc : documentSets.get(0)) {
            if(isDocumentIntersection(doc, documentSets)){
                resultSet.add(doc);
            }
        }
        return resultSet;
    }

    private static boolean isDocumentIntersection(Document doc, List<Set<Document>> documentSets){
        boolean isDocumentPresented = true;
        for (int i = 0; i < documentSets.size(); i++) {
            isDocumentPresented = checkDocumentPresentedInSet(doc, documentSets.get(i));
            if (!isDocumentPresented) {
                break;
            }
        }
        return isDocumentPresented;
    }

    private static boolean checkDocumentPresentedInSet(Document doc, Set<Document> documents){
        for (Document compareDoc : documents) {
            if (doc.equals(compareDoc)) {
                return true;
            }
        }
        return false;
    }
}
