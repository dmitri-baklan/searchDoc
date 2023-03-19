package org.example.sevice.impl;

import org.example.exception.FolderPathException;
import org.example.model.Document;
import org.example.model.Query;
import org.example.model.vektorMode.DocumentWeight;
import org.example.model.vektorMode.Vector;
import org.example.sevice.DocumentRepresenter;

import java.util.Map;
import java.util.Set;

public class Algebraic extends DocumentRepresenter {
    Map<DocumentWeight, Vector> documentVectors;

    Vector queryVector;

//    public Algebraic(String pathToTermsFile) {
//        this.setTermsFromDocument(pathToTermsFile);
//    }


    @Override
    public Set<Document> getDocumetnsByQuery(Query query) {
        return null;
    }

    @Override
    public Set<Document> addDocumentsFromFolder(String filesFolderPath)throws FolderPathException{
        documents = super.addDocumentsFromFolder(filesFolderPath);
        setTermsFromDocuments(documents);
        setDocumentVectors();
        return documents;
    }

    private void setTermsFromDocuments(Set<Document> documentSet){
        for(Document doc : documentSet){
            terms.addAll(doc.getWordsInFile());
        }
    }

    private void setDocumentVectors(){

    }
}
