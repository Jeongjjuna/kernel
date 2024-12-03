package yjh.board.member.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yjh.board.member.application.port.MemberRepository;
import yjh.board.member.domain.Member;
import yjh.board.member.infrastructure.jpa.JpaMemberRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryAdapter implements MemberRepository {
    //    private final MemoryMemberRepository memberRepository;
    private final JpaMemberRepository memberRepository;

    @Override
    public Member save(Member member) {
        return memberRepository.save(MemberEntity.from(member))
                .toDomain();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id)
                .map(MemberEntity::toDomain);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(MemberEntity::toDomain)
                .toList();
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
