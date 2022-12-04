package hwangjihun.members.controller;

import hwangjihun.members.model.Member;
import hwangjihun.members.model.dto.MemberAddDto;
import hwangjihun.members.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 1. Client Server -> 2. 회원가입 -> 3. 회원가입 완료 페이지, '확인' -> 4. Client Server 의 페이지로 돌아가기
     * Client Server 에서 경로 정보를 전달해줘야함 -> 5. (4) 에서 경로로 redirect, 가입 완료 표식 넘겨주기
     * -> 6. Client Server 의 에서 가입 완료 표식 있으면 로그인 화면으로
     * @param memberAddDto
     * @return
     */
    @GetMapping("/add")
    public String registerForm(@ModelAttribute("memberAddDto") MemberAddDto memberAddDto) {

        return "/members/add";
    }

    @PostMapping("/add")
    public String register(@Validated @ModelAttribute MemberAddDto memberAddDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        boolean isUserIdDuplicate = memberService.isUserIdDuplicate(memberAddDto.getUserId());

        Optional<Member> findMember = memberService.findByUserId(memberAddDto.getUserId());
        if (findMember.isPresent()) {
            bindingResult.addError(new FieldError("memberAddDto", "userId",
                    memberAddDto.getUserId(), false, null, null, "중복된 아이디가 있습니다."));
        }

        if (isUserIdDuplicate || bindingResult.hasErrors()) {
            return "/members/add";
        }

        Member addMember = memberService.addMember(memberAddDto);
        redirectAttributes.addAttribute("userId", addMember.getUserId());

        return "redirect:/members/{userId}/profile";
    }

    @GetMapping("{userId}/profile")
    public String profile(@PathVariable String userId, Model model) {

        Optional<Member> findMember = memberService.findByUserId(userId);
        if (findMember.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("member", findMember.get());

        return "/members/profile";
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
