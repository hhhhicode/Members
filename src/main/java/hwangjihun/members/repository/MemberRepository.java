package hwangjihun.members.repository;

import hwangjihun.members.model.Member;
import hwangjihun.members.model.UpdateParam;
import hwangjihun.members.model.cond.MemberSearchCond;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    List<Member> findAll(MemberSearchCond cond);

    Optional<Member> findById(Long id);

    Member save(Member member);

    int update(Long id, UpdateParam param);

    int deleteById(Long id);
}
