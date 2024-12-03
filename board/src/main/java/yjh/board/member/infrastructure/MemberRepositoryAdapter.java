package yjh.board.member.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yjh.board.member.application.port.MemberRepository;
import yjh.board.member.domain.Member;
import yjh.board.member.infrastructure.memory.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryAdapter implements MemberRepository {
    private final MemoryMemberRepository memoryMemberRepository;

    @Override
    public Member save(Member member) {
        return memoryMemberRepository.save(MemberEntity.from(member))
                .toDomain();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memoryMemberRepository.findById(id)
                .map(MemberEntity::toDomain);
    }

    @Override
    public List<Member> findAll() {
        return memoryMemberRepository.findAll()
                .stream()
                .map(MemberEntity::toDomain)
                .toList();
    }

    @Override
    public void delete(Long id) {
        memoryMemberRepository.delete(id);
    }
}
