package org.example.model;

import java.util.Map;

public class Vector {
    Map<String, Float> elements;

    public void addElement(String key, Float value){
        elements.put(key, value);
    }
}
