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
}
