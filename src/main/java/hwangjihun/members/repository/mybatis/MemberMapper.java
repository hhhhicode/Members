package hwangjihun.members.repository.mybatis;

import hwangjihun.members.model.Member;
import hwangjihun.members.model.cond.MemberSearchCond;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<Member> findAll(MemberSearchCond cond);
}
