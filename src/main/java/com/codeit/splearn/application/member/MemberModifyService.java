package com.codeit.splearn.application.member;

import com.codeit.splearn.application.member.provided.EmailSender;
import com.codeit.splearn.application.member.provided.MemberFinder;
import com.codeit.splearn.application.member.provided.MemberRegister;
import com.codeit.splearn.application.member.required.MemberRepository;
import com.codeit.splearn.domain.member.*;
import com.codeit.splearn.domain.shared.Email;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class MemberModifyService implements MemberRegister {
    private final MemberFinder memberFinder;
    private final MemberRepository memberRepository;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member register(MemberRegisterRequest request) {
        checkDuplicateEmail(request);

        Member member = Member.register(request, passwordEncoder);

        memberRepository.save(member);

        sendWelcomeEmail(member);

        return member;
    }

    @Override
    public Member activate(Long memberId) {
        Member member = memberFinder.find(memberId);

        member.activate();

        return memberRepository.save(member);

    }

    public Member deactivate(Long memberId) {
        Member member = memberFinder.find(memberId);

        member.deactivate();

        return memberRepository.save(member);
    }

    @Override
    public Member updateInfo(Long memberId, MemberInfoUpdateRequest request) {
        Member member = memberFinder.find(memberId);
        
        checkDuplicateProfile(member, request.profileAddress());

        member.updateInfo(request);

        return memberRepository.save(member);
    }

    private void checkDuplicateProfile(Member member, String profileAddress) {
        if (profileAddress.isEmpty()) return;

        Profile currentProfile = member.getDetail().getProfile();
        if (currentProfile != null && currentProfile.address().equals(profileAddress)) return;

        if(memberRepository.findByProfile(new Profile(profileAddress)).isPresent()) {
            throw new DuplicationProfileException("이미 존재하는 프로필 주소입니다.: " + profileAddress );
        }
    }

    private void sendWelcomeEmail(Member member) {
        emailSender.send(member.getEmail(), "등록을 완료해주세요", "아래 링크를 클릭해서 등록을 완료해주세요");
    }

    private void checkDuplicateEmail(MemberRegisterRequest request) {
        if(memberRepository.findByEmail(new Email(request.email())).isPresent()) {
            throw new DuplicateEmailException("이미 사용중인 이메일입니다." + request.email());
        }
    }
}
