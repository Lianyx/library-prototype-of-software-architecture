package object.po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private String type, toUsername, content;
    private LocalDateTime time;

    public Message() {
    }

    public Message(String type, String content) {
        this.type = type;
        this.content = content;
        this.time = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
