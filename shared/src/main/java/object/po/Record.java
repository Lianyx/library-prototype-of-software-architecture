package object.po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Record implements Serializable {
    private String username, bookId, _bookName;
    private LocalDateTime borrowTime, returnTime;

    public Record() {
    }

    public Record(String username, String bookId, LocalDateTime borrowTime) {
        this.username = username;
        this.bookId = bookId;
        this.borrowTime = borrowTime;
    }

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

    public LocalDateTime getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(LocalDateTime borrowTime) {
        this.borrowTime = borrowTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public String getBookName() {
        return _bookName;
    }

    public void setBookName(String _bookName) {
        this._bookName = _bookName;
    }
}
