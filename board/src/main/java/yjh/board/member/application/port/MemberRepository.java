package yjh.board.member.application.port;

import yjh.board.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // create , update
    Member save(Member member);

    // read
    Optional<Member> findById(Long id);

    List<Member> findAll();

    // delete
    void delete(Long id);

}
