package hwangjihun.members.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberProfileDto {

    private String userId;
    private String userName;
    private String icon;
    private String emailAddress;
    private String displayPrograms;
    private String memo;
}
