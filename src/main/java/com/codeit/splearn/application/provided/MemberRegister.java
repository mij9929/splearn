package com.codeit.splearn.application.provided;

import com.codeit.splearn.domain.Member;
import com.codeit.splearn.domain.MemberRegisterRequest;

/*
회원의 등록과 관련된 기능을 제공한다.
 */
public interface MemberRegister {
    Member register(MemberRegisterRequest request);
}
