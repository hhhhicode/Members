package hwangjihun.members.service;

import hwangjihun.members.model.Member;
import hwangjihun.members.model.cond.MemberSearchCond;
import hwangjihun.members.model.dto.MemberAddDto;
import hwangjihun.members.repository.MemberRepository;
import hwangjihun.members.repository.mybatis.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    public MemberService(MemberMapper memberMapper, MemberRepository memberRepository) {
        this.memberMapper = memberMapper;
        this.memberRepository = memberRepository;
    }

    public Member addMember(MemberAddDto memberAddDto) {
        return memberRepository.save(memberAddDto);
    }

    public Optional<Member> findByUserId(String userId) {
        MemberSearchCond cond = new MemberSearchCond();
        cond.setUserId(userId);

        return memberMapper.findAll(cond)
                .stream()
                .findAny();
    }

    public boolean isUserIdDuplicate(String userId) {
        MemberSearchCond cond = new MemberSearchCond();
        cond.setUserId(userId);
        Optional<Member> memberOptional = memberMapper.findAll(cond)
                .stream()
                .findAny();
        if (memberOptional.isPresent()) {
            return true;
        }
        return false;
    }

    public boolean isEmailAddressDuplicate(String emailAddress) {
        MemberSearchCond cond = new MemberSearchCond();
        cond.setEmailAddress(emailAddress);
        Optional<Member> memberOptional = memberMapper.findAll(cond)
                .stream()
                .findAny();
        if (memberOptional.isPresent()) {
            return true;
        }
        return false;
    }
}
