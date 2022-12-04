package hwangjihun.members.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MemberAddDto {
    @NotBlank
    @Length(min = 4, max = 20)
    private String userId;
    @NotBlank
    @Length(min = 4, max = 20)
    private String password;
    @NotBlank
    @Length(min = 1, max = 20)
    private String userName;
    @NotBlank
    @Email
    private String emailAddress;
    @Length(max = 1000)
    private String memo;
}
