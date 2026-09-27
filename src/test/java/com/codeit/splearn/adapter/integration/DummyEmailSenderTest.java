package com.codeit.splearn.adapter.integration;

import com.codeit.splearn.domain.Email;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DummyEmailSenderTest {

    @Test
    @StdIo
    void dummyEmailSender(StdOut stdOut) {
        DummyEmailSender dummyEmailSender = new DummyEmailSender();
        dummyEmailSender.send(new Email("toby@splearn.app"), "subject", "body");
        assertThat(stdOut.capturedLines()[0]).isEqualTo("DummyEmailSender: Email[address=toby@splearn.app]");
    }

}