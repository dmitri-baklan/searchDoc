package org.example.model;

import lombok.Getter;
import org.example.util.FileReader;

import java.io.File;
import java.util.List;

@Getter
public class Document {

    final String directoryPath;
    final String fileName;
    List<String> wordsInFile;
    FileReader fr;

    public Document(File file) {
        directoryPath = file.getParent();
        fileName = file.getName();
        fr = new FileReader();
        wordsInFile = FileReader.getAllWordsFromFile(directoryPath + fileName);
    }

    public String getFullPath(){
        return directoryPath + fileName;
    }

    @Override
    public String toString() {
        return "Document{" +
                "fileName='" + fileName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = directoryPath.hashCode();
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Document) {
            Document other = (Document) obj;
            return this.directoryPath.equals(other.directoryPath) && this.fileName.equals(other.fileName);
        } else {
            return false;
        }
    }

}
