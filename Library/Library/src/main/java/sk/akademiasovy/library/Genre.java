package sk.akademiasovy.library;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Genre {
    public String getGenre() {
        return genre;
    }

    @JsonProperty("genre")
    public String genre;

}