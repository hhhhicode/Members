package hwangjihun.members.service;

import hwangjihun.members.model.Member;
import hwangjihun.members.model.cond.MemberSearchCond;
import hwangjihun.members.repository.mybatis.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public Member findByUserId(String userId) {
        MemberSearchCond cond = new MemberSearchCond();
        cond.setUserId(userId);

        return memberMapper.findAll(cond)
                .stream()
                .findAny()
                .orElse(null);
    }
}
