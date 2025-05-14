package com.nike.apinike.request;

public class AuthRequest {

    final String name;

    final String password;

    public AuthRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
