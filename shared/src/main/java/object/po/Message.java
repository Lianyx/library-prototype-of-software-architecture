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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (type != null ? !type.equals(message.type) : message.type != null) return false;
        if (toUsername != null ? !toUsername.equals(message.toUsername) : message.toUsername != null) return false;
        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        return time != null ? time.equals(message.time) : message.time == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (toUsername != null ? toUsername.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
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
