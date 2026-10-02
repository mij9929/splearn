package com.codeit.splearn.domain.member;

import com.codeit.splearn.domain.AbstractEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@ToString(callSuper = true) // 현재 클래스의 toString()을 만들 때 부모 클래스의 toString() 결과도 포함해라는 뜻
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDetail extends AbstractEntity {
    @Embedded
    private Profile profile;

    private String introduction;

    private LocalDateTime registeredAt;

    private LocalDateTime activatedAt;

    private LocalDateTime deactivatedAt;

    // 접근 제한자를 통해, Member를 통해서만 스태틱으로 접근하도록
    static MemberDetail create() {
        MemberDetail memberDetail = new MemberDetail();
        memberDetail.registeredAt = LocalDateTime.now();
        return memberDetail;
    }

    void setActivatedAt() {
        Assert.isTrue(activatedAt == null, "이미 activeatedAt은 설정되었습니다.");

        this.activatedAt = LocalDateTime.now();
    }

     void deactivate() {
        Assert.isTrue(deactivatedAt == null, "이미 deactivateAt은 설정되었습니다.");

        this.deactivatedAt = LocalDateTime.now();
    }

    public void updateinfo(MemberInfoUpdateRequest updateRequest) {
        this.profile = new Profile(updateRequest.profileAddress());
        this.introduction = Objects.requireNonNull(updateRequest.introduction());
    }
}
