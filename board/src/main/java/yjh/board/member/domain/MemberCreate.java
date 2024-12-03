package yjh.board.member.domain;

import jakarta.validation.constraints.NotNull;

public record MemberCreate(
        @NotNull(message = "이름은 필수입니다.")
        String name,

        @NotNull(message = "나이는 필수입니다.")
        Integer age,

        @NotNull(message = "email 은 필수입니다.")
        String email
) {
}
