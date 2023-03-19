package org.example.model.vektorMode;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Document;

import java.io.File;

@Getter
@Setter
public class DocumentWeight extends Document {
    private Double weight;
    public DocumentWeight(File file) {
        super(file);
    }
    public DocumentWeight(Document document, Double weight) {
        super(document);
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "DocumentWeight{" +
                "weight=" + weight +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
