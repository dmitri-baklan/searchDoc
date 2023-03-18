package org.example;

public class Main {

    public static final String TEST01 = "( B && C ) && A && ( D || E )";
    public static final String TEST00 = "A || ( B && C ) || ( D || E )";
    public static final String TEST02 = "( B && C ) && A && ( D || E )";
    public static final String TEST03 = "A";
    public static final String TEST04 = "A &&&&&&&&&&&";
    public static final String TEST05 = "A & B";
    public static final String TEST06 = "& B";
    public static final String TEST07 = "( A ) && ( B )";
    public static final String TEST08 = "A & B)";
    public static void main(String[] args) {

        Console console = new Console();
        console.runMainMenu();

//        //main
//        PredicateQueryValidatorParser predicateQueryValidatorParser = new PredicateQueryValidatorParser();
//        PredicateQuery predicateQuery = null;
//        try{
//            predicateQuery = predicateQueryValidatorParser.validateQueryInput(TEST07);
//        } catch (IncorrectQueryInputException | WrongBooleanOperationException e){
//            System.out.println(e);
//        }
//        System.out.println(predicateQuery);
//        //
    }
}