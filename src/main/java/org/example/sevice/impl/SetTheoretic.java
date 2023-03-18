package org.example.sevice.impl;

import org.example.exception.FolderPathException;
import org.example.model.*;
import org.example.sevice.DocumentRepresenter;

import java.io.File;

import org.example.util.BooleanOperatorProcessor;
import org.example.util.FileReader;

import java.util.*;

public class SetTheoretic implements DocumentRepresenter {

    Set<String> terms;
    Set<Document> documents;
    FileReader fr;
//    BooleanOperatorProcessor booleanOperatorProcessor;


    public void setTermsFromDocument(String pathToTermsFile) {
        terms = new HashSet<>(FileReader.getAllWordsFromFile(pathToTermsFile));
    }

    @Override
    public Set<Document> addDocumentsFromFolder(String filesFolderPath) throws FolderPathException {
        documents = new HashSet<>();
        File[] files = getFilesFromFolder(filesFolderPath);
        if(files.length == 0){
            throw new FolderPathException("Folder is empty!");
        }
        for(File file : files){
            addDocument(file);
        }
        return documents;
    }

    @Override
    public void addDocumentByPath(String filePath) {
        addDocument(new File(filePath));
    }

    @Override
    public Set<Document> getDocumetnsByQuery(Query query) {
        PredicateQuery predicateQuery = (PredicateQuery) query;
        return getDocumentsProcessed(predicateQuery);
    }

    private Set<Document> getDocumentsProcessed(PredicateQuery predicatePredicateQuery){
        List<Set<Document>> documentSets = new ArrayList<>();
        for(PredicateSet ps : getPredicateSetProcessed(predicatePredicateQuery)){
            documentSets.add(getDocumentsBooleanOperationProcessed(ps.getDocumentSets(), ps.getBooleanOperator()));
        }
        return getDocumentsBooleanOperationProcessed(documentSets, predicatePredicateQuery.getBooleanOperator());
    }

    private List<PredicateSet> getPredicateSetProcessed(PredicateQuery predicatePredicateQuery){
        List<PredicateSet> predicateSets = new ArrayList<>();
        for(PredicateSet ps : predicatePredicateQuery.getPredicateSets()){
            predicateSets.add(matchTermsWithDocuments(ps));
        }
        return predicateSets;
    }

    private Set<Document> getDocumentsBooleanOperationProcessed(List<Set<Document>> documentSets, BooleanOperator booleanOperator){
        return BooleanOperatorProcessor.getBooleanOperationResult(
                documentSets, booleanOperator);
    }

    private PredicateSet matchTermsWithDocuments(PredicateSet ps){
        for(String term : ps.getTerms()){
            Set<Document> docSet = getDocumentSetIncludesTerm(term);
            if(!docSet.isEmpty()){
                ps.addDocumentSet(docSet);
            }
        }
        return ps;
    }

    private Set<Document> getDocumentSetIncludesTerm(String term){
        Set<Document> docSet = new HashSet<>();
        for(Document doc : documents){
            for(String word : doc.getWordsInFile()){
                if(term.equals(word)){
                    docSet.add(doc);
                    break;
                }
            }
        }
        return docSet;
    }

    private File[] getFilesFromFolder(String filesFolderPath){
        File folder = new File(filesFolderPath);
        return folder.listFiles();
    }

    private void addDocument(File file){
        if (file.isFile() && file.getName().endsWith(".txt")) {
            documents.add(new Document(file));
        }
    }
}
