package object.po;

import object.enun.OperationType;

import java.time.LocalDateTime;

public class Record {
    private String username, bookId;
    private OperationType operationType;
    private LocalDateTime borrowTime, returnTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }



}
