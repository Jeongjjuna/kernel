package yjh.board.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yjh.board.member.application.MemberService;
import yjh.board.member.domain.MemberCreate;
import yjh.board.member.presentation.response.MemberResponse;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 가입
     */
    @PostMapping
    public MemberResponse create(
            @RequestBody MemberCreate memberCreate
    ) {
        var member = memberService.save(memberCreate);
        return MemberResponse.from(member);
    }

    /**
     * 회원 전체 조회
     */
    @GetMapping
    public List<MemberResponse> findAll() {
        return memberService.findAll()
                .stream()
                .map(MemberResponse::from)
                .toList();
    }

    /**
     * 회원 단건 삭제
     */
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable(value = "id") Long id
    ) {
        memberService.delete(id);
    }


    /**
     * 회원 단건 조회
     */
    @GetMapping("/{id}")
    public MemberResponse findOne(
            @PathVariable(value = "id") Long id
    ) {
        var member = memberService.findById(id);
        return MemberResponse.from(member);
    }

}