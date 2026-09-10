package com.codeit.splearn.application;

import com.codeit.splearn.application.provided.EmailSender;
import com.codeit.splearn.application.provided.MemberRegister;
import com.codeit.splearn.application.required.MemberRepository;
import com.codeit.splearn.domain.Member;
import com.codeit.splearn.domain.MemberRegisterRequest;
import com.codeit.splearn.domain.PasswordEncoder;
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
        // check
        Member member = Member.register(request, passwordEncoder);

        memberRepository.save(member);

        emailSender.send(member.getEmail(), "등록을 완료해주세요", "아래 링크를 클릭해서 등록을 완료해주세요");

        return member;
    }
}
