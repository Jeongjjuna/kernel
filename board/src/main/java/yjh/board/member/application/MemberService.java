package yjh.board.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yjh.board.member.application.port.MemberRepository;
import yjh.board.member.domain.Member;
import yjh.board.member.domain.MemberCreate;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member save(MemberCreate memberCreate) {
        var member = Member.create(memberCreate);
        return memberRepository.save(member);
    }

    @Transactional
    public void delete(Long id) {
        var member = findById(id);
        memberRepository.delete(member.getId());
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] Member 를 찾을 수 없습니다."));
    }

}
