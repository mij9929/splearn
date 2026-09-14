package com.codeit.splearn.application;

import com.codeit.splearn.application.provided.EmailSender;
import com.codeit.splearn.application.provided.MemberRegister;
import com.codeit.splearn.application.required.MemberRepository;
import com.codeit.splearn.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberRegister {
    private final MemberRepository memberRepository;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member register(MemberRegisterRequest request) {
        checkDuplicateEmail(request);

        Member member = Member.register(request, passwordEncoder);

        memberRepository.save(member);

        sendWelcomEmail(member);

        return member;
    }

    private void sendWelcomEmail(Member member) {
        emailSender.send(member.getEmail(), "등록을 완료해주세요", "아래 링크를 클릭해서 등록을 완료해주세요");
    }

    private void checkDuplicateEmail(MemberRegisterRequest request) {
        if(memberRepository.findByEmail(new Email(request.email())).isPresent()) {
            throw new DuplicateEmailException("이미 사용중인 이메일입니다." + request.email());
        }
    }
}
