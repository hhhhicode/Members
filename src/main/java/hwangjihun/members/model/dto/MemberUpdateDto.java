package hwangjihun.members.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
public class MemberUpdateDto {

    @Length(min = 4, max = 20)
    private String userId;
    @Length(min = 4, max = 20)
    private String password;
    private String icon;
    @Length(min = 1, max = 20)
    private String userName;
    @Email
    private String emailAddress;
    private String displayPrograms;
    @Length(max = 1000)
    private String memo;
}
