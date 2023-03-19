package org.example.model.vektorMode;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Vector {
    Map<String, Double> elements;

    public Vector() {
    }

    public Vector(Map<String, Double> elements) {
        this.elements = elements;
    }

    public void addElement(String key, Double value){
        elements.put(key, value);
    }
}
