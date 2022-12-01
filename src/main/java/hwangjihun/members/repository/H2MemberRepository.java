package hwangjihun.members.repository;

import hwangjihun.members.model.Member;
import hwangjihun.members.model.UpdateParam;
import hwangjihun.members.model.cond.MemberSearchCond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class H2MemberRepository implements MemberRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public H2MemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("MEMBERS")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public List<Member> findAll(MemberSearchCond cond) {

        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return null;
    }

    @Override
    public Member save(Member member) {

        SqlParameterSource param = new BeanPropertySqlParameterSource(member);
        Number key = jdbcInsert.executeAndReturnKey(param);

        member.setId(key.longValue());

        return member;
    }

    @Override
    public int update(Long id, UpdateParam param) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}
