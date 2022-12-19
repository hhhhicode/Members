package hwangjihun.members.service;

import hwangjihun.members.model.Member;
import hwangjihun.members.model.cond.MemberSearchCond;
import hwangjihun.members.model.dto.MemberAddDto;
import hwangjihun.members.model.dto.MemberLoginDto;
import hwangjihun.members.model.dto.MemberProfileDto;
import hwangjihun.members.model.dto.MemberUpdateDto;
import hwangjihun.members.repository.MemberRepository;
import hwangjihun.members.repository.mybatis.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    public MemberService(MemberMapper memberMapper, MemberRepository memberRepository) {
        this.memberMapper = memberMapper;
        this.memberRepository = memberRepository;
    }

    @Transactional
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

    public Optional<MemberProfileDto> findByUserIdForProfile(String userId) {

        Optional<Member> findMember = findByUserId(userId);

        //Member 가 없다면
        if (findMember.isEmpty()) {
            return Optional.empty();
        }

        //Member 가 있다면
        Member member = findMember.get();
        MemberProfileDto memberProfileDto = new MemberProfileDto();
        memberProfileDto.setUserId(member.getUserId());
        memberProfileDto.setIcon(member.getIcon());
        memberProfileDto.setUserName(member.getUserName());
        memberProfileDto.setEmailAddress(member.getEmailAddress());
        memberProfileDto.setDisplayPrograms(member.getDisplayPrograms());
        memberProfileDto.setMemo(member.getMemo());

        return Optional.of(memberProfileDto);
    }

    @Transactional
    public int update(Long id, MemberUpdateDto memberUpdateDto) {

        return memberRepository.update(id, memberUpdateDto);
    }

    @Transactional
    public int delete(Long id) {

        return memberRepository.deleteById(id);
    }

    /**
     * Session Storage(members) 에서 LOGIN 컬럼을 true 로 갱신한다.
     * @param userId
     * @param password
     * @return false : 실패, <br>true : 성공
     */
    public boolean login(String userId, String password) {
        Optional<Member> findMember = findByUserId(userId);

        if (findMember.isEmpty()) {
            //TODO login 화면으로 이동
            return false;
        }

        Member member = findMember.get();
        if (!member.getPassword().equals(password)) {
            //TODO login 화면으로 이동
            return false;
        }

        if (memberRepository.login(member.getId()) == 0) {
            return false;
        }

        return true;
    }

    public Boolean isUserIdDuplicate(String userId) {
        Optional<Member> memberOptional = findByUserId(userId);
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

    public Optional<Member> login(MemberLoginDto memberLoginDto) {
        Optional<Member> findMember = findByUserId(memberLoginDto.getUserId());
        if (findMember.isEmpty()) {
            return Optional.empty();
        }

        Member member = findMember.get();
        if (!member.getPassword().equals(memberLoginDto.getPassword())) {
            return Optional.empty();
        }

        return findMember;
    }
}
