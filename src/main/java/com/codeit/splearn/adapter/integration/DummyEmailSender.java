package com.codeit.splearn.adapter.integration;

import com.codeit.splearn.application.provided.EmailSender;
import com.codeit.splearn.domain.Email;
import org.springframework.context.annotation.Fallback;
import org.springframework.stereotype.Component;

@Component
@Fallback // 해당 타입의 빈을 찾다가, 다른 빈을 찾을 수 없을 때 대체
public class DummyEmailSender implements EmailSender {

    @Override
    public void send(Email email, String subject, String body) {
        System.out.println("DummyEmailSender: " + email);
    }
}
