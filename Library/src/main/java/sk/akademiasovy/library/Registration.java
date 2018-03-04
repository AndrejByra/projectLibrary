package sk.akademiasovy.library;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Registration {
    @JsonProperty("name")
    public String name;
    @JsonProperty("surename")
    public String surename;
    @JsonProperty("username")
    public String username;
    @JsonProperty("email")
    public String email;
    @JsonProperty("postcode")
    public String postcode;
    @JsonProperty("city")
    public String city;
    @JsonProperty("password")
    public String password;
}
