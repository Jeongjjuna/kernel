package yjh.board.member.infrastructure;

import lombok.Getter;
import yjh.board.member.domain.Member;

@Getter
public class MemberEntity {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    public static MemberEntity from(Member member) {
        MemberEntity entity = new MemberEntity();
        entity.id = member.getId();
        entity.name = member.getName();
        entity.age = member.getAge();
        entity.email = member.getEmail();
        return entity;
    }

    public Member toDomain() {
        return Member.builder()
                .id(id)
                .name(name)
                .age(age)
                .email(email)
                .build();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
