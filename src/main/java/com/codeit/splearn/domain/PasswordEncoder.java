package com.codeit.splearn.domain;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String rawPassword, String passwordHash);

}
