package yjh.board.member.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Member {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    public static Member create(MemberCreate memberCreate) {
        return Member.builder()
                .name(memberCreate.name())
                .age(memberCreate.age())
                .email(memberCreate.email())
                .build();
    }
}
