package com.codeit.splearn.adapter.security;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class SecurePasswordEncoderTest {
    @Test
    void securePasswordEncoder() {
        SecurePasswordEncoder securePasswordEncoder = new SecurePasswordEncoder();
        String passwordHash = securePasswordEncoder.encode("verysecret");

        assertThat(securePasswordEncoder.matches("verysecret", passwordHash)).isTrue();
        assertThat(securePasswordEncoder.matches("wrong", passwordHash)).isFalse();
    }

}