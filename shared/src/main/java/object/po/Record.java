package object.po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Record implements Serializable {
    private String username, bookId, bookName;
    private LocalDateTime borrowTime, returnTime;
    private double penalty;

    public Record() {
    }

    public Record(String username, String bookId) {
        this.username = username;
        this.bookId = bookId;
        this.borrowTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Record{" +
                "username='" + username + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", borrowTime=" + borrowTime +
                ", returnTime=" + returnTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (username != null ? !username.equals(record.username) : record.username != null) return false;
        if (bookId != null ? !bookId.equals(record.bookId) : record.bookId != null) return false;
        if (bookName != null ? !bookName.equals(record.bookName) : record.bookName != null) return false;
        if (borrowTime != null ? !borrowTime.equals(record.borrowTime) : record.borrowTime != null) return false;
        return returnTime != null ? returnTime.equals(record.returnTime) : record.returnTime == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (bookId != null ? bookId.hashCode() : 0);
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (borrowTime != null ? borrowTime.hashCode() : 0);
        result = 31 * result + (returnTime != null ? returnTime.hashCode() : 0);
        return result;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
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
        return bookName;
    }

    public void setBookName(String _bookName) {
        this.bookName = _bookName;
    }
}
