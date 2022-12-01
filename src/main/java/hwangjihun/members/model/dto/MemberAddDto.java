package hwangjihun.members.model.dto;

import lombok.Data;

@Data
public class MemberAddDto {
    private String userId;
    private String password;
    private String userName;
    private String emailAddress;
    private String memo;
}
