import org.example.model.vektorMode.Vector;
import org.example.util.vektorMode.SimilarityMeasure;
import org.example.util.vektorMode.impl.CosineSimilarityMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CosineSimilarityMeasureTest {
    private SimilarityMeasure measure;

    @Before
    public void setUp() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        measure = new CosineSimilarityMeasure();

    }

    @Test
    public void testCountSimilarityMeasure() {
        Map<String, Double> query = new HashMap<>();
        query.put("milk", 1.0);
        query.put("dog", 2.0);
        query.put("apple", 3.0);

        Map<String, Double> document = new HashMap<>();
        document.put("milk", 3.0);
        document.put("dog", 2.0);
        document.put("apple", 3.0);

        double similarity = measure.countSimilarityMeasure(new Vector(query), new Vector(document));
        Assert.assertEquals(0.911, similarity, 0.001);
    }

    @Test
    public void testGetScalarProduct() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getScalarProduct = CosineSimilarityMeasure.class.getDeclaredMethod("getScalarProduct", Map.class, Map.class);
        getScalarProduct.setAccessible(true);

        Map<String, Double> vector1 = new HashMap<>();
        vector1.put("milk", 1.0);
        vector1.put("dog", 2.0);
        vector1.put("apple", 3.0);

        Map<String, Double> vector2 = new HashMap<>();
        vector2.put("milk", 3.0);
        vector2.put("dog", 2.0);
        vector2.put("apple", 3.0);


        double scalarProduct = (double) getScalarProduct.invoke(measure, vector1, vector2);
        Assert.assertEquals(16, scalarProduct, 0.001);
    }

    @Test
    public void testGetVectorMagnitude() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Method getVectorMagnitude = CosineSimilarityMeasure.class.getDeclaredMethod("getVectorMagnitude", Map.class);
        getVectorMagnitude.setAccessible(true);

        Map<String, Double> vector = new HashMap<>();
        vector.put("milk", 1.0);
        vector.put("dog", 2.0);
        vector.put("apple", 3.0);

        double magnitude = (double) getVectorMagnitude.invoke(measure, vector);
        Assert.assertEquals(3.741, magnitude, 0.001);
    }
}
