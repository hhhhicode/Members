package hwangjihun.members.controller;

import hwangjihun.members.model.Member;
import hwangjihun.members.model.dto.MemberLoginDto;
import hwangjihun.members.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/external/members")
public class ExternalFormMemberController {

    private final MemberService memberService;

    public ExternalFormMemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ResponseBody
    @PostMapping("/login")
    public Member login(@RequestBody MemberLoginDto memberLoginDto) {
        Optional<Member> loginMember = memberService.login(memberLoginDto);
        if (loginMember.isEmpty()) {
            return new Member();
        }

        return loginMember.get();
    }
}
