package yjh.board.member.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yjh.board.member.domain.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
