package org.example.sevice.impl;

import org.example.model.Document;
import org.example.model.PredicateQuery;
import org.example.model.Query;
import org.example.model.Vector;
import org.example.sevice.DocumentRepresenter;

import javax.print.Doc;
import java.util.Map;
import java.util.Set;

public class Algebraic implements DocumentRepresenter {
    Map<Document, Vector> documentVectors;

    @Override
    public Set<Document> getDocumetnsByQuery(Query query) {
        return null;
    }

    @Override
    public Set<Document> addDocumentsFromFolder(String filesFolderPath) {
        return null;
    }

    @Override
    public void addDocumentByPath(String filePath) {

    }
}
