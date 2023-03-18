package org.example.sevice;

import org.example.exception.FolderPathException;
import org.example.model.Document;
import org.example.model.Query;
import org.example.util.FileReader;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class DocumentRepresenter {
    protected Set<String> terms = new HashSet<>();
    protected Set<Document> documents;

    public abstract Set<Document> getDocumetnsByQuery(Query query);

    public void addDocumentByPath(String filePath){
        addDocument(new File(filePath));
    }

    public Set<String> setTermsFromDocument(String pathToTermsFile) {
        terms = new HashSet<>(FileReader.getAllWordsFromFile(pathToTermsFile));
        return terms;
    }

    public Set<Document> addDocumentsFromFolder(String filesFolderPath)throws FolderPathException{
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
