package po;

import lombok.Data;

@Data
public class UserPO {
    private String id;
    private String name;
    private String type;
    private String password;
    private int borrowingNumber;
    private int borrowingDays;
}
