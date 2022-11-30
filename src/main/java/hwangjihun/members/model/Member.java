package hwangjihun.members.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private Long id;
    private String userId;
    private String password;
    private String userName;
    private String icon;
    private String emailAddress;
    private String displayPrograms;
    private String memo;
}
