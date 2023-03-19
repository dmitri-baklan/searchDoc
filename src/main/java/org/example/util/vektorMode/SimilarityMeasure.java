package org.example.util.vektorMode;

import org.example.model.vektorMode.Vector;

public interface SimilarityMeasure {
    Double countSimilarityMeasure(Vector query, Vector document);
}
