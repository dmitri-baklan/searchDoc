//import org.example.exception.InccorrectTermException;
//import org.example.exception.IncorrectQueryInputException;
//import org.example.exception.WrongBooleanOperationException;
//import org.example.model.Query;
//import org.example.model.BooleanOperator;
//import org.example.util.QueryValidator;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.lang.reflect.Field;
//import java.net.http.WebSocketHandshakeException;
//
//import static org.junit.Assert.*;
//
//public class QueryValidatorTest {
//    QueryValidator queryValidator;
//    Query query;
//    public static final String TEST10 = "A && B";
//    public static final String TEST11 = "A || B";
//    public static final String TEST20 = "( A && B )";
//    public static final String TEST21 = "( A || B )";
//    public static final String TEST30 = "( A || B ) && C";
//    public static final String TEST31 = "( A && B ) && C";
//    public static final String TEST32 = "( A || B ) || C";
//    public static final String TEST33 = "( A && B ) || C";
//    public static final String TEST40 = "A && ( B || C )";
//    public static final String TEST41 = "A && ( B && C )";
//    public static final String TEST42 = "A || ( B && C )";
//    public static final String TEST43 = "A || ( B || C )";
//    public static final String TEST50 = "A || ( B || C ) || D";
//    public static final String TEST51 = "A && ( B && C ) && D";
//    public static final String TEST52 = "A && ( B || C ) || D";
//    public static final String TEST53 = "A || ( B && C ) || D";
//    public static final String TEST54 = "A || ( B || C ) && D";
//    public static final String TEST55 = "A && ( B && C ) || D";
//    public static final String TEST56 = "A && ( B || C ) && D";
//    public static final String TEST57 = "A || ( B && C ) && D";
//    public static final String TEST60 = "A || D || ( B || C )";
//    public static final String TEST61 = "A && D || ( B || C )";
//    public static final String TEST62 = "A || D && ( B || C )";
//    public static final String TEST63 = "A || D || ( B && C )";
//    public static final String TEST64 = "A && D && ( B || C )";
//    public static final String TEST65 = "A && D || ( B && C )";
//    public static final String TEST66 = "A || D && ( B && C )";
//    public static final String TEST67 = "A && D && ( B && C )";
//    public static final String TEST70 = "( B || C ) || A || D";
//    public static final String TEST71 = "( B && C ) || A || D";
//    public static final String TEST72 = "( B || C ) && A || D";
//    public static final String TEST73 = "( B || C ) || A && D";
//    public static final String TEST74 = "( B && C ) && A || D";
//    public static final String TEST75 = "( B && C ) || A && D";
//    public static final String TEST76 = "( B || C ) && A && D";
//    public static final String TEST77 = "( B && C ) && A && D";
//    public static final String TEST80 = "( B || C ) || ( A || D )";
//    public static final String TEST81 = "( B && C ) || ( A || D )";
//    public static final String TEST82 = "( B || C ) && ( A || D )";
//    public static final String TEST83 = "( B || C ) || ( A && D )";
//    public static final String TEST84 = "( B && C ) && ( A || D )";
//    public static final String TEST85 = "( B && C ) || ( A && D )";
//    public static final String TEST86 = "( B || C ) && ( A && D )";
//    public static final String TEST87 = "( B && C ) && ( A && D )";
//    public static final String TEST90 = "A && ( B && C ) && D";
//    public static final String TEST91 = "A || ( B && C ) && D";
//    public static final String TEST92 = "A && ( B || C ) && D";
//    public static final String TEST93 = "A && ( B && C ) || D";
//    public static final String TEST94 = "A || ( B || C ) && D";
//    public static final String TEST95 = "A || ( B && C ) || D";
//    public static final String TEST96 = "A && ( B || C ) || D";
//    public static final String TEST97 = "A || ( B || C ) || D";
//    public static final String TEST00 = "A || ( B && C ) || ( D || E )";
//    public static final String TEST01 = "( D || E ) || ( B && C ) || A";
//    public static final String TEST02 = "( B && C ) && A && ( D || E )";
//
//    @Before
//    public void init() throws NoSuchFieldException, IllegalAccessException {
//        queryValidator = new QueryValidator();
//        query = new Query();
//        Field queryField = queryValidator.getClass().getDeclaredField("query");
//        queryField.setAccessible(true);
//        queryField.set(queryValidator, query);
//    }
//
//    @Test
//    public void testValidations() throws IncorrectQueryInputException, WrongBooleanOperationException {
//        queryValidator.validateQueryInput(TEST01);
//        Assert.assertEquals(BooleanOperator.OR, query.getBooleanOperator());
//    }
//
//    @Test(expected = IncorrectQueryInputException.class)
//    public void testValidationsThrowsException() throws IncorrectQueryInputException, WrongBooleanOperationException {
//        queryValidator.validateQueryInput(TEST96);
//    }
//}
