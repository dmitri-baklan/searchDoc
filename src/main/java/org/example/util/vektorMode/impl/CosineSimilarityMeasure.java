package org.example.util.vektorMode.impl;

import org.example.model.Document;
import org.example.model.vektorMode.Vector;
import org.example.util.vektorMode.SimilarityMeasure;

import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class CosineSimilarityMeasure implements SimilarityMeasure {

    @Override
    public Double countSimilarityMeasure(Vector query, Vector document) {
        return getCosineAngleVectors(query.getElements(), document.getElements());
    }

    private double getCosineAngleVectors(Map<String, Double> queryEl, Map<String, Double> documentEl){
        double scalarProduct = getScalarProduct(queryEl, documentEl);
        double queryMagnitude = getVectorMagnitude(queryEl);
        double documentMagnitude = getVectorMagnitude(queryEl);
        return scalarProduct / (queryMagnitude * documentMagnitude);
    }

    private double getScalarProduct(Map<String, Double> queryEl, Map<String, Double> documentEl){
        double scalarProduct = 0d;
        for (String term : queryEl.keySet()){
            scalarProduct += queryEl.get(term) * documentEl.get(term);
        }
        return scalarProduct;
    }

    private double getVectorMagnitude(Map<String, Double> vector){
        return sqrt(vector.values().stream()
                .map(e -> pow(e, 2))
                .mapToDouble(Double::doubleValue)
                .sum());
    }

}
