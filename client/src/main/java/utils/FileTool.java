package utils;

import java.io.*;

public class FileTool {
    public static String readContent(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        String str;
        try {
            BufferedReader br =  new BufferedReader(new InputStreamReader(inputStream));
            while (null != (str = br.readLine())) {
                sb.append(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
