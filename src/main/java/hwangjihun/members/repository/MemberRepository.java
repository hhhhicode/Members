package hwangjihun.members.repository;

import hwangjihun.members.model.Member;
import hwangjihun.members.model.cond.MemberSearchCond;
import hwangjihun.members.model.dto.MemberAddDto;
import hwangjihun.members.model.dto.MemberUpdateDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    List<Member> findAll(MemberSearchCond cond);

    Optional<Member> findById(Long id);

    Member save(MemberAddDto addMember);

    int update(Long id, MemberUpdateDto memberUpdateDto);

    int deleteById(Long id);

    int login(Long id);
}
