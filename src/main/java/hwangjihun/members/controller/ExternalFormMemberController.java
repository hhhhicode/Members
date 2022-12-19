package hwangjihun.members.controller;

import hwangjihun.members.domain.members.MemberConst;
import hwangjihun.members.model.Member;
import hwangjihun.members.model.dto.MemberAddDto;
import hwangjihun.members.model.dto.MemberLoginDto;
import hwangjihun.members.model.dto.MemberProfileDto;
import hwangjihun.members.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/external/members")
public class ExternalFormMemberController {

    private final MemberService memberService;

    public ExternalFormMemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MemberProfileDto findByUserIdForProfile(@PathVariable String userId) {
        Optional<MemberProfileDto> findMemberProfileDto = memberService.findByUserIdForProfile(userId);

        if (findMemberProfileDto.isEmpty()) {
            return new MemberProfileDto();
        }

        return findMemberProfileDto.get();
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Member login(@RequestBody MemberLoginDto memberLoginDto) {
        Optional<Member> loginMember = memberService.login(memberLoginDto);
        if (loginMember.isEmpty()) {
            return new Member();
        }

        return loginMember.get();
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Member add(@Validated @RequestBody MemberAddDto memberAddDto, BindingResult bindingResult) {

        //Global Error Validation
        Optional<Member> findMember = memberService.findByUserId(memberAddDto.getUserId());
        if (findMember.isPresent()) {
            bindingResult.addError(new FieldError("memberAddDto", "userId",
                    memberAddDto.getUserId(), false, new String[]{"exist.memberAddDto.userId"}, null, null));
        }
        boolean isUserIdDuplicate = memberService.isUserIdDuplicate(memberAddDto.getUserId());
        if (isUserIdDuplicate || bindingResult.hasErrors()) {
            return new Member();
        }

        //Validation Pass Logic

        return memberService.addMember(memberAddDto);
    }

    @GetMapping("/{userId}/exist")
    public String userIdExistCheck(@PathVariable String userId) {
        return memberService.isUserIdDuplicate(userId).toString();
    }

    @DeleteMapping("/{userId}")
    public Boolean delete(@PathVariable String userId) {

        Optional<Member> findMember = memberService.findByUserId(userId);
        if (findMember.isEmpty()) {
            return false;
        }

        Member targetMember = findMember.get();
        int deleteNum = memberService.delete(targetMember.getId());
        if (deleteNum == 0) {
            return false;
        }

        return true;
    }
}
