package com.codeit.splearn.domain.member;

import jakarta.validation.constraints.Size;
import org.jspecify.annotations.NonNull;

public record MemberInfoUpdateRequest(
        @Size(min = 5, max = 20) String nickname,
        @Size(min = 1, max = 15) String profileAddress,
        @NonNull String introduction
) {
}
