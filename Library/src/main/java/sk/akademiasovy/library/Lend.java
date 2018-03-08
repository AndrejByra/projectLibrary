package sk.akademiasovy.library;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lend {
    @JsonProperty("idu")
    public String idu;

    @JsonProperty("idb")
    public String idb;


    public String getIdb() {
        return idb;
    }

    public String getIdu() {
        return idu;
    }
}
