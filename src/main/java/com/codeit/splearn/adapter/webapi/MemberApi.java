package com.codeit.splearn.adapter.webapi;

import com.codeit.splearn.adapter.webapi.dto.MemberRegisterResponse;
import com.codeit.splearn.application.member.provided.MemberRegister;
import com.codeit.splearn.domain.member.Member;
import com.codeit.splearn.domain.member.MemberRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApi {
    private final MemberRegister memberRegister;

    @PostMapping("/api/members")
    public MemberRegisterResponse registerMember(@RequestBody @Valid MemberRegisterRequest request) {
        Member register = memberRegister.register(request);

        return MemberRegisterResponse.of(register);
    }
}
