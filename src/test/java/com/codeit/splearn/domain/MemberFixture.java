package com.codeit.splearn.domain;

public class MemberFixture {
    public static MemberRegisterRequest createMemberRegisterRequest(String email) {
        return new MemberRegisterRequest(email, "Toby", "secret");
    }

    public static MemberRegisterRequest createMemberRegisterRequest() {
        return createMemberRegisterRequest("toby@splearn.app");
    }

    public static PasswordEncoder createPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String rawPassword, String passwordHash) {
                return encode(rawPassword).equals(passwordHash);
            }
        };
    }
}
