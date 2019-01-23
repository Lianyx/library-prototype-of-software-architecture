package utils;

import lombok.Getter;

@Getter
public enum UserType {
    ADMINISTRATOR("管理员"),
    TEACHER("教师"),
    UNDERGRADUATE("在校生"),
    GRADUATE("毕业生")
    ;

    private String name;

    UserType(String name) {
        this.name = name;
    }

    public static String[] getUserTypeList() {
        return new String[] {
                ADMINISTRATOR.name,
                TEACHER.name,
                UNDERGRADUATE.name,
                GRADUATE.name
        };
    }
}
