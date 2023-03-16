package org.example.model;

import java.util.Map;

public class Vector {
    Map<String, Float> termWeights;

    public void addElement(String key, Float value){
        termWeights.put(key, value);
    }
}
