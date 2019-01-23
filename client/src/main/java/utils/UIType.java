package utils;

public enum UIType {
    ADD("添加"),
    USER_EDIT("用户编辑"),
    ADMIN_EDIT("管理员编辑"),
    BOOK_MANAGEMENT("书本管理"),
    BOOK_READER("阅读器")
    ;

    private String description;

    UIType(String description) {
        this.description = description;
    }
}
