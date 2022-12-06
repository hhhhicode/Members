package hwangjihun.members.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MemberLoginDto {

    @Length(min = 4, max = 20)
    private String userId;
    @Length(min = 4, max = 20)
    private String password;
}
