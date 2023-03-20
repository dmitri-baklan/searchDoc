

import org.example.model.Document;
import org.example.model.Query;
import org.example.model.vektorMode.Vector;
import org.example.model.vektorMode.WordQuery;
import org.example.util.FileReader;
import org.example.util.vektorMode.impl.CountIDFWeightCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
//import java.util.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CountIDFWeightCalculatorTest {
    private final Set<Document> testDocumentSet = new HashSet<>();
//    private final List<String> testWords = Arrays.asList("apple", "banana", "cherry", "plane");
    private final Set<String> testTerms = new HashSet<>(Arrays.asList("apple", "banana", "cherry", "durian", "elderberry"));
    private final WordQuery testQuery = new WordQuery(List.of("apple", "banana", "cherry", "car", "apple"));
    private final CountIDFWeightCalculator testCalculator = new CountIDFWeightCalculator(testDocumentSet);

    File file;
    FileReader fr;
    Document document1;
    Document document2;
    Document document3;


    @Before
    public void setUp() {
        file = Mockito.mock(File.class);
        document1 = Mockito.mock(Document.class);
        document2 = Mockito.mock(Document.class);
        document3 = Mockito.mock(Document.class);

        when(document1.getWordsInFile()).thenReturn(List.of("apple", "banana", "elderberry", "apple"));
        when(document2.getWordsInFile()).thenReturn(List.of("apple", "banana", "durian"));
        when(document3.getWordsInFile()).thenReturn(List.of("cherry", "durian", "elderberry"));
        testDocumentSet.addAll(Arrays.asList(document1, document2, document3));
    }

    @Test
    public void testGetDocumentVectorForTerms() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = CountIDFWeightCalculator.class.getDeclaredMethod("getDocumentVectorForTerms", Set.class, Document.class);
        method.setAccessible(true);

        Vector result = (Vector) method.invoke(testCalculator, testTerms, document1);

        Assert.assertEquals(0.810, result.getElements().get("apple"), 0.001);
        Assert.assertEquals(0.405, result.getElements().get("banana"), 0.001);
        Assert.assertEquals(0d, result.getElements().get("cherry"), 0.001);
        Assert.assertEquals(0d, result.getElements().get("durian"), 0.001);
        Assert.assertEquals(0.405, result.getElements().get("elderberry"), 0.001);
    }

    @Test
    public void testGetQueryVectorForTerms() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = CountIDFWeightCalculator.class.getDeclaredMethod("getQueryVectorForTerms", Set.class, Query.class);
        method.setAccessible(true);

        Vector result = (Vector) method.invoke(testCalculator, testTerms, testQuery);

        Assert.assertEquals(0.810, result.getElements().get("apple"), 0.001);
        Assert.assertEquals(0.405, result.getElements().get("banana"), 0.001);
        Assert.assertEquals(1.098, result.getElements().get("cherry"), 0.001);
        Assert.assertEquals(0d, result.getElements().get("durian"), 0.001);
        Assert.assertEquals(0d, result.getElements().get("elderberry"), 0.001);
    }

    @Test
    public void testIsDocumentContainTerm() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = CountIDFWeightCalculator.class.getDeclaredMethod("isDocumentContainTerm", String.class, Document.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(testCalculator, "apple", document1);

        Assert.assertTrue(result);
    }

    @Test
    public void testGetTermIdfValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = CountIDFWeightCalculator.class.getDeclaredMethod("getTermIdfValue", String.class);
        method.setAccessible(true);

        double result = (double) method.invoke(testCalculator, "apple");

        double expected = Math.log(3.0 / 2.0);

        Assert.assertEquals(expected, result, 0.001);
    }



    @Test
    public void testGetTfValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getTfValueMethod = CountIDFWeightCalculator.class.getDeclaredMethod("getTfValue", String.class, List.class);
        getTfValueMethod.setAccessible(true);

        List<String> words = Arrays.asList("word1", "word2", "word3", "word1");
        double result = (double) getTfValueMethod.invoke(testCalculator, "word1", words);

        Assert.assertEquals(2.0, result, 0.001);
    }
    
    public void setFileForDoc(String parent, String name, List<String> words){
        when(file.getParent()).thenReturn(parent);
        when(file.getName()).thenReturn(name);
        try (MockedStatic<FileReader> utilities = Mockito.mockStatic(FileReader.class)) {
            utilities.when(() -> FileReader.getAllWordsFromFile(anyString())).thenReturn(words);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}