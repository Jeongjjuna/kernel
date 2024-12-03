package yjh.board.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yjh.board.member.application.port.MemberRepository;
import yjh.board.member.domain.Member;
import yjh.board.member.domain.MemberCreate;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member save(MemberCreate memberCreate) {
        var member = Member.create(memberCreate);
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Member Not Found"));
    }

}
