package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DocumentSet {
    private List<Document> documents;
    private String term;
}
