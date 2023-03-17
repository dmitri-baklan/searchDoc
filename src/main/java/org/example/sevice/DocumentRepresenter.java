package org.example.sevice;

import org.example.exception.FolderPathException;
import org.example.model.Document;
import org.example.model.PredicateQuery;
import org.example.model.Query;

import java.util.Set;

public interface DocumentRepresenter {
    public Set<Document> getDocumetnsByQuery(Query query);

    public void addDocumentsFromFolder(String filesFolderPath)throws FolderPathException;

    public void addDocumentByPath(String filePath);
}
