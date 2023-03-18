package org.example.model.booleanMode;

import org.example.model.Query;

import java.util.*;

//@Getter
public class PredicateQuery implements Query {
    List<PredicateSet> predicateSets;
    BooleanOperator booleanOperator;

    public PredicateQuery() {
        predicateSets = new ArrayList<>();
    }

    public List<PredicateSet> getPredicateSets() {
        for (PredicateSet ps : predicateSets) {
            if (ps.getTerms().size() == 1) {
                ps.setBooleanOperator(booleanOperator);
            }
        }
        return predicateSets;
    }

    public BooleanOperator getBooleanOperator() {
        return booleanOperator;
    }

    public void addPredicateSet(PredicateSet predicateSet) {
        predicateSet.setBooleanOperator(chooseBooleanOperatorsToPredicateSetIfOneTerm(predicateSet));
        predicateSets.add(predicateSet);
    }

    public void setBooleanOperator(BooleanOperator booleanOperator) {
        this.booleanOperator = booleanOperator;
        for (PredicateSet ps : predicateSets) {
            ps.setBooleanOperator(chooseBooleanOperatorsToPredicateSetIfOneTerm(ps));
        }
    }

    private BooleanOperator chooseBooleanOperatorsToPredicateSetIfOneTerm(PredicateSet ps){
        return ((ps.getTerms().size() == 1) && booleanOperator != null) ? booleanOperator : ps.getBooleanOperator();
    }

    @Override
    public String toString() {
        String out = "Query{" +
                "predicateSets=" + predicateSets +
                ", booleanOperator=" + booleanOperator +
                '}';

        return out;
    }
}
