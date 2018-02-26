package sk.akademiasovy.library;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {
    public String getAuthorname() {
        return author;
    }

    @JsonProperty("author")
    public String author;
}

