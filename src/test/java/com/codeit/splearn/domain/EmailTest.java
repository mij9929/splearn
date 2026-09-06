package com.codeit.splearn.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void equality() {
        var email1 = new Email("toby@spleran.app");
        var email2 = new Email("toby@spleran.app");

        assertThat(email1).isEqualTo(email2);
    }
}