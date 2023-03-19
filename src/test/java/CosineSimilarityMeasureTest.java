import org.example.model.vektorMode.Vector;
import org.example.util.vektorMode.SimilarityMeasure;
import org.example.util.vektorMode.impl.CosineSimilarityMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CosineSimilarityMeasureTest {
    private SimilarityMeasure measure;

    @Before
    public void setUp() {
        measure = new CosineSimilarityMeasure();
    }

    @Test
    public void testCountSimilarityMeasure() {
        Map<String, Double> query = new HashMap<>();
        query.put("milk", 1.0);
        query.put("dog", 1.0);
        query.put("apple", 1.0);

        Map<String, Double> document = new HashMap<>();
        document.put("milk", 1.0);
        document.put("dog", 1.0);
        document.put("apple", 1.0);

        double similarity = measure.countSimilarityMeasure(new Vector(query), new Vector(document));
        Assert.assertEquals(1, similarity, 0.001);
    }

//    @Test
//    public void testGetScalarProduct() {
//        Map<String, Double> vector1 = new HashMap<>();
//        vector1.put("milk", 1.0);
//        vector1.put("dog", 1.0);
//        vector1.put("apple", 1.0);
//
//        Map<String, Double> vector2 = new HashMap<>();
//        vector2.put("milk", 1.0);
//        vector2.put("wolf", 1.0);
//        vector2.put("apple", 1.0);
//
//        double scalarProduct = new CosineSimilarityMeasure().getScalarProduct(vector1, vector2);
//        Assert.assertEquals(2.0, scalarProduct);
//    }
//
//    @Test
//    public void testGetVectorMagnitude() {
//        Map<String, Double> vector = new HashMap<>();
//        vector.put("milk", 1.0);
//        vector.put("dog", 1.0);
//        vector.put("apple", 1.0);
//
//        double magnitude = new CosineSimilarityMeasure().getVectorMagnitude(vector);
//        Assert.assertEquals(3.0, magnitude);
//    }
}
