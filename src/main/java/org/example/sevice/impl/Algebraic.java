package org.example.sevice.impl;

import org.example.model.Document;
import org.example.model.Query;
import org.example.model.vektorMode.DocumentWeight;
import org.example.model.vektorMode.Vector;
import org.example.sevice.DocumentRepresenter;

import java.util.Map;
import java.util.Set;

public class Algebraic extends DocumentRepresenter {
    Map<DocumentWeight, Vector> documentVectors;

    @Override
    public Set<Document> getDocumetnsByQuery(Query query) {
        return null;
    }

}
