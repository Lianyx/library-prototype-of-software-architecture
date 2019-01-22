package utils;


import lombok.Getter;

@Getter
public enum SystemInfo {
    SYSTEM_NAME("图书馆信息管理系统"),
    ;

    private String value;

    SystemInfo(String value) {
        this.value = value;
    }
}
