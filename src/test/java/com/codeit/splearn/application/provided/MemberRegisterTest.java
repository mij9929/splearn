package com.codeit.splearn.application.provided;

import com.codeit.splearn.application.MemberService;
import com.codeit.splearn.application.required.MemberRepository;
import com.codeit.splearn.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class MemberRegisterTest {
    @Test
    void registerTestStub() {
        MemberRegister register = new MemberService(
                new MemberRepositoryStub(),
                new EmailSenderStub(),
                MemberFixture.createPasswordEncoder()
        );

        Member member = register.register(MemberFixture.createMemberRegisterRequest());

        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void registerTestMock() {
        EmailSenderMock emailSenderMock = new EmailSenderMock();

        MemberRegister register = new MemberService(
                new MemberRepositoryStub(),
                emailSenderMock,
                MemberFixture.createPasswordEncoder()
        );

        Member member = register.register(MemberFixture.createMemberRegisterRequest());

        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);

        assertThat(emailSenderMock.getEmails()).hasSize(1);
        assertThat(emailSenderMock.getEmails().getFirst()).isEqualTo(member.getEmail());
    }

    @Test
    void registerTestMockito() {
        EmailSenderMock emailSenderMock = Mockito.mock(EmailSenderMock.class);

        MemberRegister register = new MemberService(
                new MemberRepositoryStub(),
                emailSenderMock,
                MemberFixture.createPasswordEncoder()
        );

        Member member = register.register(MemberFixture.createMemberRegisterRequest());

        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);

        Mockito.verify(emailSenderMock).send(eq(member.getEmail()), any(), any());

    }

    static class MemberRepositoryStub implements MemberRepository {
        @Override
        public Member save(Member member) {
            ReflectionTestUtils.setField(member, "id", 1L);
            return member;
        }
    }


    static class EmailSenderStub implements EmailSender {
        @Override
        public void send(Email email, String subject, String body) {

        }
    }

    static class EmailSenderMock implements EmailSender {
        List<Email> emails = new ArrayList<>();

        public List<Email> getEmails() {
            return emails;
        }

        @Override
        public void send(Email email, String subject, String body) {
            emails.add(email);
        }
    }
}