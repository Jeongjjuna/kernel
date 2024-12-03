package yjh.board.member.presentation.response;

import lombok.Builder;
import yjh.board.member.domain.Member;

@Builder
public record MemberResponse(
        Long id,
        String name,
        Integer age,
        String email
) {
    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .email(member.getEmail())
                .build();
    }
}
