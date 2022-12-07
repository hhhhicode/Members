package hwangjihun.members.repository;

import hwangjihun.members.model.Member;
import hwangjihun.members.model.cond.MemberSearchCond;
import hwangjihun.members.model.dto.MemberAddDto;
import hwangjihun.members.model.dto.MemberUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        String sql = "SELECT * FROM members WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);

        return Optional.of(jdbcTemplate.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(Member.class)));
    }

    @Override
    public Member save(MemberAddDto addMember) {

        SqlParameterSource param = new BeanPropertySqlParameterSource(addMember);
        Number key = jdbcInsert.executeAndReturnKey(param);

        Member member = new Member();
        member.setId(key.longValue());
        member.setUserId(addMember.getUserId());
        member.setPassword(addMember.getPassword());
        member.setUserName(addMember.getUserName());
        member.setEmailAddress(addMember.getEmailAddress());
        member.setMemo(addMember.getMemo());

        return member;
    }



    @Override
    public int login(Long id) {
        String sql = "UPDATE members SET login = true WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);

        return jdbcTemplate.update(sql, param);
    }

    @Override
    public int update(Long id, MemberUpdateDto memberUpdateDto) {

        String sql = "UPDATE members SET user_id=:userId, password=:password, " +
                "icon=:icon, user_name=:userName, " +
                "email_address=:emailAddress, display_programs=:displayPrograms, memo=:memo " +
                "WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", memberUpdateDto.getUserId())
                .addValue("password", memberUpdateDto.getPassword())
                .addValue("icon", memberUpdateDto.getIcon())
                .addValue("userName", memberUpdateDto.getUserName())
                .addValue("emailAddress", memberUpdateDto.getEmailAddress())
                .addValue("displayPrograms", memberUpdateDto.getDisplayPrograms())
                .addValue("memo", memberUpdateDto.getMemo())
                .addValue("id", id);

        return jdbcTemplate.update(sql, param);
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

}
