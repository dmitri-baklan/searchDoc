package org.example.model.vektorMode;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Vector {
    Map<String, Double> elements;

    public Vector() {
        elements = new HashMap<>();
    }

    public Vector(Map<String, Double> elements) {
        this.elements = elements;
    }

    public void addElement(String key, Double value){
        elements.put(key, value);
    }
}
