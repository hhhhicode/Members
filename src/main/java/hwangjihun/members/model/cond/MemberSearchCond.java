package hwangjihun.members.model.cond;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MemberSearchCond {
    private Long id;
    private String userId;
    private String userName;
    private String emailAddress;
    private String memo;
}
