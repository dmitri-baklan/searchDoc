package org.example.model.vektorMode;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Query;

import java.util.List;

@Getter
@Setter
public class WordQuery implements Query {
    List<String> words;

    public WordQuery(List<String> words) {
        this.words = words;
    }
}
