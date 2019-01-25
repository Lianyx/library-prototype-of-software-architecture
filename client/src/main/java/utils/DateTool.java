package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTool {
    public static String DateFormat(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return time == null ? "" : time.format(formatter);
    }
}
