package org.example.model.vektorMode;

import java.util.Map;

public class Vector {
    Map<String, Float> elements;

    public void addElement(String key, Float value){
        elements.put(key, value);
    }
}
