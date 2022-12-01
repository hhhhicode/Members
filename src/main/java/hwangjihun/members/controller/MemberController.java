package hwangjihun.members.controller;

import hwangjihun.members.model.Member;
import hwangjihun.members.model.dto.MemberAddDto;
import hwangjihun.members.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/add")
    public String registerForm(@ModelAttribute("memberAddDto") MemberAddDto memberAddDto) {

        return "/members/add";
    }

    @PostMapping("/add")
    public String register(@ModelAttribute MemberAddDto memberAddDto) {
        boolean isUserIdDuplicate = memberService.isUserIdDuplicate(memberAddDto.getUserId());
        //TODO Validation
        if (isUserIdDuplicate) {
            return "/members/add";
        }

        Member addMember = memberService.addMember(memberAddDto);
        return "redirect:/";
    }

    @GetMapping("/{userId}/exist")
    public ResponseEntity<Boolean> isUserIdDuplicate(@PathVariable String userId) {
        return ResponseEntity.ok(memberService.isUserIdDuplicate(userId));
    }

    @GetMapping("/{emailAddress}/exist")
    public ResponseEntity<Boolean> isEmailAddressDuplicate(@PathVariable String emailAddress) {
        return ResponseEntity.ok(memberService.isEmailAddressDuplicate(emailAddress));
    }
}
