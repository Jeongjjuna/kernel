package yjh.board.member.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import yjh.board.member.infrastructure.MemberEntity;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {
}
