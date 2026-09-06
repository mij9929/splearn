package com.codeit.splearn.application.required;

import com.codeit.splearn.domain.Member;
import com.codeit.splearn.domain.MemberFixture;
import com.codeit.splearn.domain.MemberRegisterRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static com.codeit.splearn.domain.MemberFixture.createMemberRegisterRequest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("멤버 생성 테스트")
    void createMember() {
        // given
        Member member = Member.register(createMemberRegisterRequest(),  MemberFixture.createPasswordEncoder());

        // when
        memberRepository.save(member);

        assertThat(member.getId()).isNotNull();
        entityManager.flush();

        // then


    }
}