package sk.akademiasovy.library;

import java.util.Random;

public class User {
    private String name;
    private String surename;
    private String username;
    private String email;
    private String phone;
    private String adress;
    private String postcode;
    private String city;
    private String token;

    public User( String name, String surename, String username, String email, String phone, String adress,String postcode, String city) {
        this.name = name;
        this.surename = surename;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.postcode = postcode;
        this.city = city;
        generateToken();
    }

    private void generateToken() {
        char[] text=new char[40];
        Random random=new Random();

        for(int i=0;i<40;i++){
            text[i]=(char) (random.nextInt(26)+'A');
        }
        token=String.valueOf(text);

        System.out.println(token);
    }

    public String getName() {
        return name;
    }

    public String getSurename() {
        return surename;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return adress;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String getToken() {
        return token;
    }
}
