package utils;


import lombok.Getter;

@Getter
public enum SystemInfo {
    SYSTEM_NAME("图书馆信息管理系统"),
    RML_BASE_URL("rmi://127.0.0.1:1099/")
    ;

    private String value;

    SystemInfo(String value) {
        this.value = value;
    }
}
