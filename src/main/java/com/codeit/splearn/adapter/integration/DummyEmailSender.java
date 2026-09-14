package com.codeit.splearn.adapter.integration;

import com.codeit.splearn.application.provided.EmailSender;
import com.codeit.splearn.domain.Email;

public class DummyEmailSender implements EmailSender {

    @Override
    public void send(Email email, String subject, String body) {
        System.out.println("DummyEmailSender: " + email);
    }
}
