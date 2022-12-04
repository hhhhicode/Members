package hwangjihun.members.controller;

import hwangjihun.members.model.Member;
import hwangjihun.members.model.dto.MemberAddDto;
import hwangjihun.members.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
    public String register(@Validated @ModelAttribute MemberAddDto memberAddDto, BindingResult bindingResult) {
        boolean isUserIdDuplicate = memberService.isUserIdDuplicate(memberAddDto.getUserId());
//TODO Validation
        if (isUserIdDuplicate || bindingResult.hasErrors()) {
            return "/members/add";
        }
//TODO 요청 URI 를 받아서 그쪽으로 redirect 시켜야함.
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
