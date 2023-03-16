package org.example.sevice;

import org.example.model.Document;
import org.example.model.PredicateQuery;

import java.util.Set;

public interface DocumentRepresenter {
    public Set<Document> getDocumetnsByQuery(PredicateQuery predicateQuery);

    public void addDocumentsFromFolder(String filesFolderPath);

    public void addDocumentByPath(String filePath);
}
