package sk.akademiasovy.library;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookName {
    public String getBookname() {
        return bookname;
    }

    @JsonProperty("bookname")
    public String bookname;
}
