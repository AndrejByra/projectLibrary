package sk.akademiasovy.library;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Credentials {
    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

