package com.jungdam.auth.dto.request;

public class AuthRequestDto {

    private String username;
    private String password;

    protected AuthRequestDto() {

    }

    public AuthRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}