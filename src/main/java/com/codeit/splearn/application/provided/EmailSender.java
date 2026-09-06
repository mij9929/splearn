package com.codeit.splearn.application.provided;

/*
이메일을 발송한다.
 */
public interface EmailSender {
    void send(String email, String subject, String body);
}
