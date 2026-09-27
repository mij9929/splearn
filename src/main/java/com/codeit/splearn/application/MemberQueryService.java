package com.codeit.splearn.application;

import com.codeit.splearn.application.provided.MemberFinder;
import com.codeit.splearn.application.required.MemberRepository;
import com.codeit.splearn.domain.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class MemberQueryService implements MemberFinder {
    private final MemberRepository memberRepository;

    @Override
    public Member find(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("active member not found. id: " + memberId));
    }
}
