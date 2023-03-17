package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class DocumentWeight extends Document{
    private float weight;
    public DocumentWeight(File file) {
        super(file);
    }

    @Override
    public String toString() {
        return "DocumentWeight{" +
                "weight=" + weight +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
